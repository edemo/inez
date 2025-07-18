package io.github.magwas.inez.storage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.storage.repository.BridiReferenceRepository;

@Service
public class RemoveReferencesService {

	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	public void apply(String bridiId, List<String> references) {
		for (int i = 0; i < references.size(); i++) {
			String ref = references.get(i);
			bridiReferenceRepository.deleteBybridiIdAndPositionAndSumtiId(bridiId, i,
					ref);
		}

	}

}
