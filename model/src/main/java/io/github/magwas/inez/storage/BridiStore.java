package io.github.magwas.inez.storage;

import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;

@Service
public class BridiStore {

	@Autowired
	BridiRepository bridiRepository;

	@Autowired
	BridiIndexRepository bridiIndexRepository;

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
		Bridi old = bridiRepository.findById(bridi.getId()).orElse(null);
		Bridi retVal = bridiRepository.save(bridi);

		removeOldReferences(bridi);

		saveNewReferences(retVal);
		history.add(new StoreCommand(BridiStoreOperation.SAVE, old, bridi));
		return retVal;
	}

	private void removeOldReferences(Bridi bridi) {
		List<String> references = bridi.getReferences();
		if (!references.isEmpty()) {
			String selbriId = references.get(0);
			for (int i = 0; i < references.size(); i++) {
				String id = createIdForIndex(selbriId, references.get(i), i);
				BridiIndex refs = bridiIndexRepository.findById(id)
						.orElse(new BridiIndex(id));
				refs.getReferences().remove(bridi.getId());
				bridiIndexRepository.save(refs);
			}
		}
	}

	private void saveNewReferences(Bridi retVal) {
		List<String> references = retVal.getReferences();
		if (!references.isEmpty()) {
			String selbriId = references.get(0);
			for (int i = 0; i < references.size(); i++) {
				String id = createIdForIndex(selbriId, references.get(i), i);
				BridiIndex refs = bridiIndexRepository.findById(id)
						.orElse(new BridiIndex(id));
				refs.getReferences().add(retVal.getId());
				bridiIndexRepository.save(refs);
			}
		}
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

	public Stream<Bridi> findAllByRepresentation(String string) {
		Iterable<Bridi> allByRepresentation = bridiRepository
				.findAllByRepresentation(string);
		return StreamSupport.stream(allByRepresentation.spliterator(), false);
	}

	public void undo() {
		StoreCommand last = history.removeLast();
		if (null != last.old)
			bridiRepository.save(last.old);
		else
			bridiRepository.delete(last.now);
	}

	public Stream<Bridi> getBridiBySelbriAndSumtiIds(String selbriId,
			String nthSumtiiId, int n) {
		String id = createIdForIndex(selbriId, nthSumtiiId, n);
		Optional<BridiIndex> index = bridiIndexRepository.findById(id);
		if (index.isEmpty())
			return Stream.of();
		Set<String> idList = index.get().getReferences();
		Iterable<Bridi> allById = bridiRepository.findAllById(idList);
		return StreamSupport.stream(allById.spliterator(), false);
	}

	private String createIdForIndex(String selbriId, String nthSumtiiId, int n) {
		String id = selbriId + "{" + nthSumtiiId + "{" + n;
		return id;
	}

}
