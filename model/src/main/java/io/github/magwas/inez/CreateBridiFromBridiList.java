package io.github.magwas.inez;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class CreateBridiFromBridiList {

	@Autowired
	BridiRepository bridiRepository;

	public Bridi apply(List<String> bridiList) {
		String sumtiRepresentation = bridiRepository.findById(bridiList.get(0))
				.get().representation;
		Object[] selbrireprs = bridiList.subList(1, bridiList.size()).stream()
				.map(id -> {
					return "{" + bridiRepository.findById(id).get().representation + "}";
				}).toArray();
		String repr = MessageFormat.format(sumtiRepresentation, selbrireprs);
		return new Bridi(repr, repr, bridiList);
	}

}
