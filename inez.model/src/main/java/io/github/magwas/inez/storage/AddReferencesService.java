package io.github.magwas.inez.storage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.parse.IdUtil;
import io.github.magwas.inez.storage.model.BridiReference;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;

@Service
public class AddReferencesService {
	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	public void apply(final String id, final List<String> references) {
		if (null == references || references.isEmpty())
			return;
		String selbriId = references.get(0);
		for (int i = 0; i < references.size(); i++) {
			String sumtiId = references.get(i);
			String referenceId = IdUtil.createID(id + i);
			BridiReference reference = new BridiReference(referenceId, id, selbriId,
					i, sumtiId);
			bridiReferenceRepository.save(reference);
		}
	}

}
