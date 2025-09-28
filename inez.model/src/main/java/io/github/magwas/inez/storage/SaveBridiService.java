package io.github.magwas.inez.storage;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.BridiStoreOperation;
import io.github.magwas.inez.storage.model.Sumti;
import io.github.magwas.inez.storage.repository.SumtiRepository;
import io.github.magwas.runtime.LogUtil;

@Service
public class SaveBridiService implements Function<Bridi, Bridi> {
	@Autowired
	SumtiRepository sumtiRepository;

	@Autowired
	NotifyStoreChangeService notifyStoreChange;

	@Autowired
	CreateBridiFromSumtiService createBridiFromSumti;

	@Autowired
	RemoveReferencesService removeReferences;

	@Autowired
	AddReferencesService addReferences;

	public Set<Bridi> apply(final Collection<Bridi> values) {
		Set<Bridi> ret = new HashSet<>();
		for (Bridi bridi : values) {
			ret.add(apply(bridi));
		}
		return ret;
	}

	@Override
	public Bridi apply(final Bridi bridi) {
		Sumti old = sumtiRepository.findById(bridi.id()).orElse(null);
		Bridi oldBridi = null;
		if (null != old) {
			oldBridi = createBridiFromSumti.apply(old);
			LogUtil.debug("old", old, oldBridi);
			removeReferences.apply(oldBridi.id(), oldBridi.references());
		}
		Sumti sumti = new Sumti(bridi.id(), bridi.representation());
		sumtiRepository.save(sumti);
		addReferences.apply(bridi.id(), bridi.references());
		notifyStoreChange.apply(BridiStoreOperation.SAVE, oldBridi, bridi);
		return oldBridi;
	}
}
