package io.github.magwas.inez.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.storage.model.Sumti;
import io.github.magwas.inez.storage.repository.SumtiRepository;

@Service
public class CreateSumtiService {
	@Autowired
	SumtiRepository sumtiRepository;

	public Sumti apply(final String id, final String representation) {
		Sumti sumti = new Sumti(id, representation);
		sumtiRepository.save(sumti);
		return sumti;
	}

}
