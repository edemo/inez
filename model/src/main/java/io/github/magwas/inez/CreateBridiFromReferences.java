package io.github.magwas.inez;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.storage.BridiStore;
import lombok.NonNull;

@Service
public class CreateBridiFromReferences {

	@Autowired
	BridiStore bridiStore;

	public Bridi apply(final @NonNull List<String> bridiList) {
		String sumtiRepresentation = bridiStore.findById(bridiList.get(0))
				.get().representation;
		Object[] selbrireprs = bridiList.subList(1, bridiList.size()).stream()
				.map(id -> {
					return "{" + bridiStore.findById(id).get().representation + "}";
				}).toArray();
		String repr = MessageFormat.format(sumtiRepresentation, selbrireprs);
		return new Bridi(repr, repr, bridiList);
	}

}
