package io.github.magwas.inez.storage;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.storage.repository.SumtiRepository;

@Service
public class FindAllByRepresentation {
	@Autowired
	SumtiRepository sumtiRepository;
	@Autowired
	CreateBridiFromSumti createBridiFromSumti;

	public Stream<Bridi> apply(String representation) {
		return sumtiRepository.findAllByRepresentation(representation).stream()
				.map(sumti -> createBridiFromSumti.apply(sumti));
	}

}
