package io.github.magwas.inez.element;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.storage.model.BridiReference;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;

@Service
public class GetBridiElementChildrenService implements ElementConstants {
	@Autowired
	GetRelativeForBridiElementService getRelativeForBridiElement;

	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	@Autowired
	BridiElementFactory bridiElementFactory;

	public Stream<BridiElement> apply(final String id) {
		final Stream<String> contained = getRelativeForBridiElement
				.apply(id, CONTAINS_ID, 1, 2)
				.filter(x -> {
					Optional<BridiReference> refP = bridiReferenceRepository.findByBridiIdAndPosition(x, 0);
					return refP.isEmpty() || !CONTAINS_ID.equals(refP.get().selbriId());
				});
		return contained.sorted().map(bridiElementFactory::apply);
	}
}
