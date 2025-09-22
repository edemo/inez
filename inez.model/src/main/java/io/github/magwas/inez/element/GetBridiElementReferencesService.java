package io.github.magwas.inez.element;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetBridiElementReferencesService {
	@Autowired
	GetBridiElementReferenceIdsService getBridiElementReferenceIds;
	@Autowired
	BridiElementFactory bridiElementFactory;

	public Stream<BridiElement> apply(String id) {
		return getBridiElementReferenceIds.apply(id)
				.map(x -> bridiElementFactory.apply(x));
	}

}
