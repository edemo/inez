package io.github.magwas.inez.element;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.storage.repository.BridiReferenceRepository;

@Service
public class GetRelativeForBridiElementService {
	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	public Stream<String> apply(final String id, String selbriId,
			final int fromPosition, final int toPosition) {
		return bridiReferenceRepository
				.findAllBySelbriIdAndSumtiIdAndPosition(selbriId, id, fromPosition)
				.stream().map(x -> x.bridiId()).map(x -> bridiReferenceRepository
						.findByBridiIdAndPosition(x, toPosition))
				.filter(x -> !x.isEmpty()).map(x -> x.get().sumtiId());
	}

}