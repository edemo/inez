package io.github.magwas.inez.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.runtime.LogUtil;

@Service
public class IsOfTypeService implements ElementConstants {

	@Autowired
	GetBridiElementTypeIdService getBridiElementTypeId;

	boolean apply(String id, final String typeId) {
		LogUtil.debug("IsOfTypeService", id, typeId);
		String theId = id;
		for (int i = 0; i < MAX_TYPE_DEPTH; i++) {
			if (theId.equals(typeId)) return true;
			if (THING_ID.equals(theId)) return false;
			theId = getBridiElementTypeId.apply(theId);
			LogUtil.debug("id:", theId);
		}
		throw new Error("Probable type loop");
	}
}
