package io.github.magwas.inez.storage;

import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;

@Service
public class BridiStore {

	@Autowired
	BridiRepository bridiRepository;

	Deque<StoreCommand> history = new ConcurrentLinkedDeque<StoreCommand>();

	public void save(final Bridi bridi) {
		Optional<Bridi> old = bridiRepository.findById(bridi.getId());
		bridiRepository.save(bridi);
		history.add(
				new StoreCommand(BridiStoreOperation.SAVE, old.orElse(null), bridi));
	}

	public void delete(final Bridi bridi) {
		Optional<Bridi> old = bridiRepository.findById(bridi.getId());
		if (old.isEmpty())
			throw new NoSuchElementException("no such bridi:" + bridi);
		bridiRepository.delete(bridi);
		history.add(
				new StoreCommand(BridiStoreOperation.DELETE, old.orElse(null), null));
	}

	public Optional<Bridi> findById(String string) {
		return bridiRepository.findById(string);
	}

	public void undo() {
		StoreCommand last = history.removeLast();
		if (null != last.old)
			bridiRepository.save(last.old);
		else
			bridiRepository.delete(last.now);
	}

}
