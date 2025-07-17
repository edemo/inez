package io.github.magwas.inez.storage;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.BridiStoreOperation;
import io.github.magwas.inez.storage.model.Sumti;
import io.github.magwas.inez.storage.repository.SumtiRepository;

@Service
public class DeleteBridi {

	@Autowired
	SumtiRepository sumtiRepository;
	@Autowired
	RemoveReferences removeReferences;
	@Autowired
	NotifyStoreChange notifyStoreChange;

	public Bridi apply(Bridi bridi) {
		Optional<Sumti> oldP = sumtiRepository.findById(bridi.id());
		if (oldP.isEmpty())
			throw new NoSuchElementException();
		removeReferences.apply(bridi.references());
		sumtiRepository.delete(new Sumti(bridi.id(), bridi.representation()));
		notifyStoreChange.apply(BridiStoreOperation.DELETE, bridi, null);
		return bridi;
	}

}
