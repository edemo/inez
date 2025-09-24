package io.github.magwas.inez.element;

import java.util.Set;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.storage.model.BridiReference;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;
import io.github.magwas.runtime.LogUtil;

@Service
public class GetRelativeForBridiElementService {
	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	public Stream<String> apply(final String id, final String selbriId,
                                final int fromPosition, final int toPosition) {
		LogUtil.debug("findAllBySelbriIdAndSumtiIdAndPosition(", selbriId, id,
				fromPosition);
		Set<BridiReference> found = bridiReferenceRepository
				.findAllBySelbriIdAndSumtiIdAndPosition(selbriId, id, fromPosition);
		LogUtil.debug("found:", found);
		return found.stream().map(BridiReference::bridiId).map(
				x -> bridiReferenceRepository.findByBridiIdAndPosition(x, toPosition))
				.filter(x -> x.isPresent()).map(x -> x.get().sumtiId());
	}

}