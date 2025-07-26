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

	public Stream<BridiElement> apply(String id) {
		final Stream<String> contained = getRelativeForBridiElement
				.apply(id, CONTAINS_ID, 1, 2).filter(x -> {
					Optional<BridiReference> refP = bridiReferenceRepository
							.findByBridiIdAndPosition(x, 0);
					if (refP.isEmpty())
						return true;
					if (refP.get().selbriId().equals(CONTAINS_ID))
						return false;
					return true;
				});
		return contained.sorted().map(x -> BridiElement.byId((x)));
	}

}