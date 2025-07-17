package io.github.magwas.inez.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.InezUtil;
import io.github.magwas.inez.impl.LogUtil;
import io.github.magwas.inez.storage.model.Sumti;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;
import io.github.magwas.inez.storage.repository.SumtiRepository;

@Service
public class BridiStore {

	@Autowired
	BridiStoreChangeListeners bridiStoreChangeListeners;

	@Autowired
	BridiStoreHistory bridiStoreHistory;

	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	@Autowired
	CreateBridiFromSumti createBridiFromSumti;

	@Autowired
	SumtiRepository sumtiRepository;

	@Autowired
	SaveBridi saveBridi;

	@Autowired
	DeleteBridi deleteBridi;

	@Autowired
	FindBridiById findBridiById;

	public Bridi delete(final Bridi bridi) {
		return deleteBridi.apply(bridi);
	}

	public Optional<Bridi> findById(String id) {
		return findBridiById.apply(id);
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
		return all.stream().map(sumti -> createBridiFromSumti.apply(sumti));
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

		return new Bridi(InezUtil.createID(top), top, references);
	}

	public Bridi save(Bridi bridi) {
		return saveBridi.apply(bridi);
	}

	public Set<Bridi> save(Collection<Bridi> bridis) {
		return saveBridi.apply(bridis);
	}

}
