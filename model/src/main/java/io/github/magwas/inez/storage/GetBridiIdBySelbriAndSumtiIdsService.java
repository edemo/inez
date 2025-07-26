package io.github.magwas.inez.storage;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.storage.repository.BridiReferenceRepository;

@Service
public class GetBridiIdBySelbriAndSumtiIdsService {
	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	public Stream<String> apply(String selbriId, String sumtiId, int position) {
		return bridiReferenceRepository
				.findAllBySelbriIdAndSumtiIdAndPosition(selbriId, sumtiId, position)
				.stream().map(x -> x.bridiId());
	}

}
