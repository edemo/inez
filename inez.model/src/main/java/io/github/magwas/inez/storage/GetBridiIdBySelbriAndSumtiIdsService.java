package io.github.magwas.inez.storage;

import java.util.stream.Stream;

import io.github.magwas.inez.storage.model.BridiReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.storage.repository.BridiReferenceRepository;

@Service
public class GetBridiIdBySelbriAndSumtiIdsService {
	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	public Stream<String> apply(final String selbriId, final String sumtiId, final int position) {
		return bridiReferenceRepository
				.findAllBySelbriIdAndSumtiIdAndPosition(selbriId, sumtiId, position)
				.stream().map(BridiReference::bridiId);
	}

}
