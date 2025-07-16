package io.github.magwas.inez.storage;

import static io.github.magwas.inez.LogUtil.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.ParseText;
import io.github.magwas.inez.ParserOutput;

@Service
public class QueryProcessor implements StorageConstants {

	@Autowired
	ParseText parseText;
	@Autowired
	BridiStore bridiStore;

	public Set<Bridi> apply(String query) {
		debug("\n\napply(" + query);
		ParserOutput parserOutput = parseText.apply(query);
		return apply(parserOutput);
	}

	public Set<Bridi> apply(ParserOutput parserOutput) {
		String top = parserOutput.getTop();
		return query(top, parserOutput.getReferenceMap())
				.collect(Collectors.toSet());
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
			return getBridiByReference(top);
		} else {
			return bridiStore.findAllByRepresentation(top);
		}
	}

	private Stream<Bridi> resolveBridi(String top,
			Map<String, List<String>> referenceMap) {
		debug("resolveBridi(" + top);
		List<Bridi> byRepresentation = bridiStore.findAllByRepresentation(top)
				.toList();
		if (!byRepresentation.isEmpty()) {
			return byRepresentation.stream();
		}
		List<String> partList = referenceMap.get(top);
		List<Set<String>> foundIds = new ArrayList<>();
		int notAnyIndex = 0;
		for (int i = 1; i < partList.size(); i++) {
			final String sumti = partList.get(i);
			if (!sumti.equals(QUERY_BRIDI_ID))
				notAnyIndex = i;
			Stream<Bridi> sumtiStream = query(sumti, referenceMap);
			Stream<String> sumtiIdStream = sumtiStream.map(bridi -> bridi.getId());
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
		final int partIndex = notAnyIndex;
		String selbriId = partList.get(0);
		Set<String> sumtiIdSet = foundIds.get(partIndex - 1);
		debug("finding candidates for " + top, partIndex, partList);
		Stream<Bridi> candidates = Stream.of();
		for (String sumtiId : sumtiIdSet) {
			debug("getBridiBySelbriAndSumtiIds#2(" + selbriId, sumtiId, partIndex);
			Stream<Bridi> candidatesForOne = bridiStore
					.getBridiBySelbriAndSumtiIds(selbriId, sumtiId, partIndex);
			candidatesForOne = candidatesForOne.peek(bridi -> debug("#2>", selbriId,
					sumtiId, notAnyIndex - 1 + partIndex, bridi));
			candidates = Stream.concat(candidates, candidatesForOne);
		}
		return candidates;
	}

	private Stream<Bridi> filterCandidates(List<String> partList,
			List<Set<String>> foundForSelbries, Stream<Bridi> candidates) {
		for (int j = 1; j < partList.size(); j++) {
			final int sumtiIndex = j - 1;
			final int referenceIndex = j;
			String sumti = partList.get(referenceIndex);
			if (!QUERY_BRIDI_ID.equals(sumti)) {
				Set<String> allowableSumtiIdSet = foundForSelbries.get(sumtiIndex);
				debug("filter setup", referenceIndex, allowableSumtiIdSet, partList);
				candidates = candidates.filter(bridi -> {
					List<String> references = bridi.getReferences();
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
		Optional<Bridi> bridiP = bridiStore.findById(top.substring(1));
		if (bridiP.isEmpty())
			matchingBridis = Stream.of();
		else
			matchingBridis = Stream.of(bridiP.get());
		return matchingBridis;
	}

}