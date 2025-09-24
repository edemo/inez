package io.github.magwas.inez.element;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.storage.model.BridiReference;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;
import io.github.magwas.inez.storage.repository.SumtiRepository;

@Service
public class GetBridiElementParentService implements ElementConstants {
	@Autowired
	GetRelativeForBridiElementService getRelativeForBridiElement;
	@Autowired
	SumtiRepository sumtiRepository;
	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	public String apply(final String id) {
		List<String> parents = getRelativeForBridiElement.apply(id, CONTAINS_ID, 2, 1).toList();
        if (!parents.isEmpty()) {
            return parents.getFirst();
        }

        Set<BridiReference> selbriref = bridiReferenceRepository.findAllByBridiIdAndPosition(id, 1);
        if (!selbriref.isEmpty()) {
            BridiReference ref = selbriref.iterator().next();
            if (ref.selbriId().equals(CONTAINS_ID)) {
                return ref.sumtiId();
            }
        }

        return null;
    }

}
