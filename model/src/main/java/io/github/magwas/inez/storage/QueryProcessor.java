package io.github.magwas.inez.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.BridiSet;
import io.github.magwas.inez.ParseText;

@Service
public class QueryProcessor implements StorageConstants {

	@Autowired
	public ParseText parseText;
	@Autowired
	public BridiStore bridiStore;

	public Set<Bridi> apply(String query) {
		BridiSet bridiSet = parseText.apply(query);
		Bridi theBridi = bridiSet.getBridis().get(bridiSet.getTopId());
		List<String> reflist = theBridi.getReferences();
		if (reflist.isEmpty())
			return Set.of(theBridi);
		Set<Bridi> candidates = queryWithUnknowns(reflist);
		return candidates;
	}

	private Set<Bridi> queryWithUnknowns(List<String> reflist) {
		if (reflist.isEmpty())
			return Set.of();
		String selbriId = reflist.get(0);
		List<List<String>> bridiLists = getIdListForAllSumties(reflist, selbriId);

		for (int j = 1; j < reflist.size() - 1; j++) {
			bridiLists = permutate(bridiLists, j);
			if (null == bridiLists) {
				return Set.of();
			}
		}

		Iterable<Bridi> bridiBySelbriAndSumtiIds = bridiStore
				.getBridiBySelbriAndSumtiIds(selbriId, selbriId, 0);

		Stream<Bridi> candidates = StreamSupport
				.stream(bridiBySelbriAndSumtiIds.spliterator(), false);
		for (int j = 0; j < reflist.size(); j++) {
			final String nthSumtiId = reflist.get(j);
			if (!(nthSumtiId.equals(QUERY_BRIDI_ID)
					|| nthSumtiId.contains("{" + QUERY_BRIDI_ID + "}"))) {
				final int k = j;
				candidates = candidates
						.filter(x -> x.getReferences().get(k).equals(nthSumtiId));
			}
		}
		candidates = candidates.filter(x -> x.getLongTerm() == true);
		return candidates.collect(Collectors.toSet());
	}

	private List<List<String>> getIdListForAllSumties(List<String> reflist,
			String selbriId) {
		List<List<String>> bridiLists = new ArrayList<>();
		for (int i = 1; i < reflist.size(); i++) {
			final String nthSumtiId = reflist.get(i);
			List<String> results;
			if (nthSumtiId.equals(QUERY_BRIDI_ID)) {
				results = List.of(QUERY_BRIDI_ID);
			} else {
				Bridi sumti = bridiStore.findById(nthSumtiId).get();
				List<String> references = sumti.getReferences();
				if (!references.isEmpty())
					results = queryWithUnknowns(references).stream()
							.map(bridi -> bridi.getId()).toList();
				else
					results = List.of(sumti.getId());
			}
			bridiLists.add(results);
		}
		return bridiLists;
	}

	private List<List<String>> permutate(List<List<String>> bridiLists,
			int position) {
		// lists before position are permutated and uniform length
		List<String> current = bridiLists.get(position);
		int currentLength = current.size();
		if (1 == currentLength)
			return bridiLists;
		if (0 == currentLength) {
			return null;
		}
		int prevLength = bridiLists.get(position - 1).size();
		List<String> newCurrent = new ArrayList<>();
		for (int i = 0; i < prevLength; i++) {
			newCurrent.addAll(current);
		}
		bridiLists.set(position, newCurrent);
		for (int listIndex = 0; listIndex < position; listIndex++) {
			List<String> newList = new ArrayList<>();
			for (int i = 0; i < prevLength; i++) {
				newList.addAll(current);
			}
			bridiLists.set(position, newList);
		}
		return bridiLists;
	}
}
