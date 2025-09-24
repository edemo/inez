package io.github.magwas.inez.storage;

import java.util.stream.Stream;

import io.github.magwas.inez.storage.model.Sumti;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.storage.repository.SumtiRepository;

@Service
public class FindAllIdByRepresentationService {
	@Autowired
	SumtiRepository sumtiRepository;

	public Stream<String> apply(String representation) {
		return sumtiRepository.findAllByRepresentation(representation).stream()
				.map(Sumti::id);
	}

}
