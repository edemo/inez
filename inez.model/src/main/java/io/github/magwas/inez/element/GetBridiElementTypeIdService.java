package io.github.magwas.inez.element;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.runtime.LogUtil;

@Service
public class GetBridiElementTypeIdService implements ElementConstants {

	@Autowired
	GetRelativeForBridiElementService getRelativeForBridiElement;

	public String apply(String id) {
		List<String> types = getRelativeForBridiElement.apply(id, IS_A_ID, 1, 2)
				.toList();
		if (types.size() > 1) {
			throw new Error("more parents for " + id + ":" + types);
		}
		String type = THING_ID;
		if (!types.isEmpty())
			type = types.get(0);
		LogUtil.debug("type:" + type);
		return type;
	}

}
