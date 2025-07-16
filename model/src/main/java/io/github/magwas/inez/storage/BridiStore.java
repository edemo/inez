package io.github.magwas.inez.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.LogUtil;
import io.github.magwas.inez.model.Bridi;

@Service
public class BridiStore {

	@Autowired
	SumtiRepository sumtiRepository;

	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	Deque<StoreCommand> history = new ConcurrentLinkedDeque<StoreCommand>();

	Map<String, Map<String, Map<Integer, List<String>>>> selbriSumtiMap = new HashMap<>();

	public Set<Bridi> save(Collection<Bridi> values) {
		Set<Bridi> ret = new HashSet<>();
		for (Bridi bridi : values) {
			ret.add(save(bridi));
		}
		return ret;
	}

	public Bridi save(final Bridi bridi) {
//		Bridi old = bridiRepository.findById(bridi.getId()).orElse(null);
		doSave(bridi);

//		removeOldReferences(bridi);

//		saveNewReferences(bridi);
//		history.add(new StoreCommand(BridiStoreOperation.SAVE, old, bridi));
		return bridi;
	}

	private void removeOldReferences(Bridi bridi) {
	}

	private void saveNewReferences(Bridi retVal) {
	}

	public void delete(final Bridi bridi) {
//		history.add(
//				new StoreCommand(BridiStoreOperation.DELETE, old.orElse(null), null));
	}

	public Optional<Bridi> findById(String id) {
		Optional<Sumti> sumtiP = sumtiRepository.findById(id);
		if (sumtiP.isEmpty())
			return Optional.empty();
		return Optional.of(createBridiFromSumti(sumtiP.get()));
	}

	public void undo() {
//		StoreCommand last = history.removeLast();
//		if (null != last.old)
//			bridiRepository.save(last.old);
//		else
//			bridiRepository.delete(last.now);
	}

	public Stream<String> getBridiIdBySelbriAndSumtiIds(String selbriId,
			String sumtiId, int position) {
		Set<String> bridiesWithSumti = bridiReferenceRepository
				.findAllBySumtiIdAndPosition(selbriId, 0).stream()
				.map(ref -> ref.bridiId()).collect(Collectors.toSet());
		return bridiReferenceRepository
				.findAllBySumtiIdAndPosition(sumtiId, position).stream()
				.mapMulti((bridiReference, consumer) -> {
					if (bridiesWithSumti.contains(bridiReference.bridiId())) {
						consumer.accept(bridiReference.bridiId());
					}
				});
	}

	public Stream<Bridi> findAllByRepresentation(String representation) {
		Set<Sumti> all = sumtiRepository.findAllByRepresentation(representation);
		LogUtil.debug("all:", all);
		return all.stream().map(sumti -> createBridiFromSumti(sumti));
	}

	private Bridi createBridiFromSumti(Sumti sumti) {
		final Map<Integer, BridiReference> map = new HashMap<>();
		Set<BridiReference> refs = bridiReferenceRepository
				.findAllByBridiId(sumti.id());
		LogUtil.debug("refs:" + refs);
		refs.forEach(ref -> map.put(ref.position(), ref));
		LogUtil.debug("map:" + map);
		List<String> parts = new ArrayList<>();
		int i = 0;
		while (map.containsKey(i)) {
			parts.add(map.get(i).sumtiId());
			i++;
		}
		Bridi bridi = new Bridi(sumti.id(), sumti.representation(), parts);
		LogUtil.debug("bridi:", bridi);
		return bridi;
	}

	public String createID(String reference) {
		return reference;
		// return UUID.nameUUIDFromBytes(reference.getBytes()).toString();
	}

	private void doSave(Bridi bridi) {
		String bridiId = bridi.id();
		Sumti sumti = new Sumti(bridiId, bridi.representation());
		sumtiRepository.save(sumti);
		List<String> bridiReferences = bridi.references();
		if (null != bridiReferences)
			for (int i = 0; i < bridiReferences.size(); i++) {
				String sumtiId = bridiReferences.get(i);
				String referenceId = createID(bridiId + i + sumtiId);
				BridiReference reference = new BridiReference(referenceId, bridiId, i,
						sumtiId);
				bridiReferenceRepository.save(reference);
			}
	}

	public Bridi createBridiFromRepresentations(String top,
			List<String> representations) {
		List<String> references = new ArrayList<>();
		if (null != representations)
			for (int i = 0; i < representations.size(); i++) {
				String sumtiRepr = representations.get(i);
				String sumtiId = sumtiRepository.findAllByRepresentation(sumtiRepr)
						.iterator().next().id();
				references.add(sumtiId);
			}

		return new Bridi(createID(top), top, references);
	}

}
