package io.github.magwas.inez.element;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.storage.model.Sumti;
import io.github.magwas.inez.storage.repository.SumtiRepository;

@Service
public class GetBridiElementRepresentationService {
	@Autowired
	SumtiRepository sumtiRepository;

	public String apply(final String id) throws Error {
		Optional<Sumti> sumtiP = sumtiRepository.findById(id);
		if (sumtiP.isEmpty()) {
			sumtiRepository.findAll().forEach(x -> System.err.println("s:" + x));
			throw new Error("no representation for: " + id);
		}
		return sumtiP.get().representation();
	}

}