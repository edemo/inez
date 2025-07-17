package io.github.magwas.inez.storage;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.BridiStoreOperation;
import io.github.magwas.inez.impl.LogUtil;
import io.github.magwas.inez.storage.model.Sumti;
import io.github.magwas.inez.storage.repository.SumtiRepository;

@Service
public class SaveBridi {
	@Autowired
	SumtiRepository sumtiRepository;
	@Autowired
	NotifyStoreChange notifyStoreChange;
	@Autowired
	CreateBridiFromSumti createBridiFromSumti;
	@Autowired
	RemoveReferences removeReferences;
	@Autowired
	AddReferences addReferences;

	public Set<Bridi> apply(Collection<Bridi> values) {
		Set<Bridi> ret = new HashSet<>();
		for (Bridi bridi : values) {
			ret.add(apply(bridi));
		}
		return ret;
	}

	public Bridi apply(final Bridi bridi) {
		Sumti old = sumtiRepository.findById(bridi.id()).orElse(null);
		Bridi oldBridi = null;
		if (null != old) {
			oldBridi = createBridiFromSumti.apply(old);
			LogUtil.debug("old", old, oldBridi);
			removeReferences.apply(oldBridi.references());
		}
		Sumti sumti = new Sumti(bridi.id(), bridi.representation());
		sumtiRepository.save(sumti);
		addReferences.apply(bridi.id(), bridi.references());
		notifyStoreChange.apply(BridiStoreOperation.SAVE, oldBridi, bridi);
		return oldBridi;
	}

}
