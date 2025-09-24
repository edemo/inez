package io.github.magwas.inez.element;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IsInstanceService implements ElementConstants {

	@Autowired
	GetRelativeForBridiElementService getRelativeForBridiElement;

	public boolean apply(final String objectId, final String typeId) {
		if (THING_ID.equals(typeId))
			return true;
		List<String> relatives = getRelativeForBridiElement
				.apply(objectId, IS_A_ID, 1, 2).distinct().toList();
		if (relatives.contains(typeId))
			return true;
		for (String relative : relatives) {
			if (!THING_ID.equals(relative) && apply(relative, typeId))
				return true;
		}
		return false;
	}

}
