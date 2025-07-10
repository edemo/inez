package io.github.magwas.inez;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BridiStore {

	@Autowired
	BridiRepository sumtiRepository;

	List<StoreCommand> history = new ArrayList<>();

	void save(Bridi bridi) {
		sumtiRepository.save(bridi);
		history.add(new StoreCommand(BridiStoreOperation.SAVE, bridi.id));
	}

	void delete(Bridi bridi) {
		sumtiRepository.delete(bridi);
		history.add(new StoreCommand(BridiStoreOperation.DELETE, bridi.id));
	}

}
