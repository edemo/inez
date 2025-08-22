package io.github.magwas.inez.storage;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.storage.model.Sumti;
import io.github.magwas.inez.storage.repository.SumtiRepository;

@Service
public class FindBridiByIdService {

	@Autowired
	CreateBridiFromSumtiService createBridiFromSumti;

	@Autowired
	SumtiRepository sumtiRepository;

	public Optional<Bridi> apply(String id) {
		Optional<Sumti> sumtiP = sumtiRepository.findById(id);
		if (sumtiP.isEmpty())
			return Optional.empty();
		return Optional.of(createBridiFromSumti.apply(sumtiP.get()));
	}

}
