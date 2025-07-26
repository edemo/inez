package io.github.magwas.inez.storage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.parse.ParserConstants;
import io.github.magwas.inez.storage.model.BridiReference;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;

@Service
public class AddReferencesService {
	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	public void apply(String id, List<String> references) {
		if (null != references) {
			String selbriId = references.get(0);
			for (int i = 0; i < references.size(); i++) {
				String sumtiId = references.get(i);
				String referenceId = ParserConstants.createID(id + i);
				BridiReference reference = new BridiReference(referenceId, id, selbriId,
						i, sumtiId);
				bridiReferenceRepository.save(reference);
			}
		}
	}

}
