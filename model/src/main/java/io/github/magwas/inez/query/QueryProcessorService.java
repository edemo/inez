package io.github.magwas.inez.query;

import static io.github.magwas.runtime.LogUtil.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.parse.ParseTextService;
import io.github.magwas.inez.parse.ParserConstants;
import io.github.magwas.inez.parse.ParserOutput;
import io.github.magwas.inez.storage.FindAllByRepresentationService;
import io.github.magwas.inez.storage.FindAllIdByRepresentationService;
import io.github.magwas.inez.storage.FindBridiByIdService;
import io.github.magwas.inez.storage.GetBridiIdBySelbriAndSumtiIdsService;
import io.github.magwas.runtime.LogUtil;

@Service
public class QueryProcessorService
		implements Function<ParserOutput, Stream<Bridi>> {

	@Autowired
	ParseTextService parseText;
	@Autowired
	FindAllByRepresentationService findAllByRepresentation;
	@Autowired
	FindAllIdByRepresentationService findAllIdByRepresentation;
	@Autowired
	GetBridiIdBySelbriAndSumtiIdsService getBridiIdBySelbriAndSumtiIds;
	@Autowired
	FindBridiByIdService findBridiById;

	public Stream<Bridi> apply(String query) {
		return parseText.apply(query).map(x -> apply(x)).flatMap(x -> x);
	}

	public Stream<Bridi> apply(ParserOutput parserOutput) {
		String top = parserOutput.top();
		LogUtil.debug("top:" + top);
		return query(top, parserOutput.referenceMap());
	}

	private Stream<Bridi> query(String top,
			Map<String, List<String>> referenceMap) {
		debug("query(" + top);
		if (!referenceMap.containsKey(top)) {
			return resolveSumti(top);
		}
		return resolveBridi(top, referenceMap);
	}

	private Stream<Bridi> resolveSumti(String top) {
		debug("resolveSumti(" + top);
		if (top.startsWith("@")) {
			debug("byRef");
			return getBridiByReference(top);
		} else {
			debug("byStore");
			return findAllByRepresentation.apply(top);
		}
	}

	private Stream<Bridi> resolveBridi(String top,
			Map<String, List<String>> referenceMap) {
		debug("resolveBridi(" + top);
		List<Bridi> byRepresentation = findAllByRepresentation.apply(top).toList();
		if (!byRepresentation.isEmpty()) {
			return byRepresentation.stream();
		}
		List<String> partList = referenceMap.get(top);
		List<Set<String>> foundIds = new ArrayList<>();
		int notAnyIndex = 0;
		for (int i = 1; i < partList.size(); i++) {
			final String sumti = partList.get(i);
			if (!sumti.equals(ParserConstants.QUERY_BRIDI_ID))
				notAnyIndex = i;
			Stream<Bridi> sumtiStream = query(sumti, referenceMap);
			Stream<String> sumtiIdStream = sumtiStream.map(bridi -> bridi.id());
			Set<String> sumtiIds = sumtiIdStream.collect(Collectors.toSet());
			foundIds.add(sumtiIds);
		}
		Stream<Bridi> candidates = findCandidates(top, partList, notAnyIndex,
				foundIds);
		candidates = filterCandidates(partList, foundIds, candidates);
		return candidates;

	}

	private Stream<Bridi> findCandidates(String top, List<String> partList,
			int notAnyIndex, List<Set<String>> foundIds) {
		debug("findCandidates(" + top, partList, notAnyIndex, foundIds);
		if (notAnyIndex == 0)
			throw new ParseCancellationException("only anys in bridi:" + top);
		String representation = partList.get(0);
		debug("findAllIdByRepresentation(" + representation);
		return findAllIdByRepresentation.apply(representation)
				.peek(x -> debug("-1>", x))
				.mapMulti((String selbri,
						Consumer<Stream<Bridi>> consumer) -> findCandidatesForOne(selbri,
								consumer, top, partList, notAnyIndex, foundIds))
				.flatMap(c -> c).peek(x -> debug("-2>", x));
	}

	private void findCandidatesForOne(String selbriId,
			Consumer<Stream<Bridi>> consumer, String top, List<String> partList,
			int notAnyIndex, List<Set<String>> foundIds) {
		Set<String> sumtiIdSet = foundIds.get(notAnyIndex - 1);
		debug("findCandidatesForOne(" + top, notAnyIndex, partList);
		for (String sumtiId : sumtiIdSet) {
			debug("getBridiBySelbriAndSumtiIds(" + selbriId, sumtiId, notAnyIndex);
			Stream<Bridi> candidatesForOne = getBridiIdBySelbriAndSumtiIds
					.apply(selbriId, sumtiId, notAnyIndex).peek(x -> debug("-id", x))
					.map(id -> {
						debug("finding", id);
						return findBridiById.apply(id).get();
					});
			candidatesForOne = candidatesForOne.peek(bridi -> debug("#2>", selbriId,
					sumtiId, notAnyIndex - 1 + notAnyIndex, bridi));
			consumer.accept(candidatesForOne);
		}
	}

	private Stream<Bridi> filterCandidates(List<String> partList,
			List<Set<String>> foundForSelbries, Stream<Bridi> candidates) {
		for (int j = 1; j < partList.size(); j++) {
			final int sumtiIndex = j - 1;
			final int referenceIndex = j;
			String sumti = partList.get(referenceIndex);
			if (!ParserConstants.QUERY_BRIDI_ID.equals(sumti)) {
				Set<String> allowableSumtiIdSet = foundForSelbries.get(sumtiIndex);
				debug("filter setup", referenceIndex, allowableSumtiIdSet, partList,
						sumti);
				candidates = candidates.filter(bridi -> {
					List<String> references = bridi.references();
					if (references.size() <= referenceIndex)
						return false;
					String matchedId = references.get(referenceIndex);
					debug("filtering", referenceIndex, allowableSumtiIdSet, matchedId,
							partList, bridi);
					return allowableSumtiIdSet.contains(matchedId);
				});
			}
		}
		return candidates;
	}

	private Stream<Bridi> getBridiByReference(String top) {
		Stream<Bridi> matchingBridis;
		Optional<Bridi> bridiP = findBridiById.apply(top.substring(1));
		if (bridiP.isEmpty())
			matchingBridis = Stream.of();
		else
			matchingBridis = Stream.of(bridiP.get());
		return matchingBridis;
	}

}