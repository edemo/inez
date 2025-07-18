package io.github.magwas.inez.storage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.InezUtil;
import io.github.magwas.inez.storage.model.BridiReference;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;

@Service
public class AddReferencesService {
	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	public void apply(String id, List<String> references) {
		if (null != references)
			for (int i = 0; i < references.size(); i++) {
				String sumtiId = references.get(i);
				String referenceId = InezUtil.createID(id + i + sumtiId);
				BridiReference reference = new BridiReference(referenceId, id, i,
						sumtiId);
				bridiReferenceRepository.save(reference);
			}
	}

}
