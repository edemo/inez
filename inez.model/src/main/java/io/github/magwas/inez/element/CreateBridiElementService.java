package io.github.magwas.inez.element;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.parse.IdUtil;
import io.github.magwas.inez.storage.FindBridiByIdService;
import io.github.magwas.inez.storage.SaveBridiService;

@Service
public class CreateBridiElementService implements ElementConstants {

	@Autowired
	SaveBridiService saveBridi;
	@Autowired
	FindBridiByIdService findBridiById;
	@Autowired
	BridiElementFactory bridiElementFactory;

	public BridiElement apply(String containerId, String typeId,
			String representation, String... references) {
		String elementId = IdUtil.createID(representation);
		List<String> refs = List.of();
		if (references != null) {
			for (String ref : references) {
				if (findBridiById.apply(ref).isEmpty())
					throw new IllegalArgumentException("nonexisting reference:" + ref);
			}
			refs = List.of(references);
		}
		Bridi element = new Bridi(elementId, representation, refs);
		String isAId = IdUtil.createID(representation + "isA");
		Optional<Bridi> typeP = findBridiById.apply(typeId);
		if (typeP.isEmpty())
			throw new IllegalArgumentException("unknown type:" + typeId);
		Bridi type = new Bridi(isAId, MessageFormat.format(IS_A_REPR,
				representation, typeP.get().representation()),
				List.of(IS_A_ID, elementId, typeId));
		Optional<Bridi> containerP = findBridiById.apply(containerId);
		if (containerP.isEmpty())
			throw new IllegalArgumentException("unknown container:" + containerId);
		String containsId = IdUtil.createID(representation + "Contains");
		Bridi location = new Bridi(
				containsId, MessageFormat.format(CONTAINS_REPR,
						containerP.get().representation(), representation),
				List.of(CONTAINS_ID, containerId, elementId));
		saveBridi.apply(List.of(element, type, location));
		return bridiElementFactory.apply(elementId);
	}

}
