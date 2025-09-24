package io.github.magwas.inez.element;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.storage.repository.BridiReferenceRepository;

@Service
public class GetBridiElementReferenceIdsService {

	@Autowired
	BridiReferenceRepository bridiReferenceRepository;

	public Stream<String> apply(final String id) {
		return bridiReferenceRepository
				.findAllByBridiId(id).stream().sorted((arg0, arg1) -> Integer
						.valueOf(arg0.position()).compareTo(arg1.position()))
				.map(x -> x.sumtiId());
	}

}
