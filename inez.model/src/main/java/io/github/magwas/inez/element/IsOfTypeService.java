package io.github.magwas.inez.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.runtime.LogUtil;

@Service
public class IsOfTypeService implements ElementConstants {

	@Autowired
	GetBridiElementTypeIdService getBridiElementTypeId;

	boolean apply(String id, String typeId) {
		LogUtil.debug("IsOfTypeService", id, typeId);
		for (int i = 0; i < MAX_TYPE_DEPTH; i++) {
			if (id.equals(typeId))
				return true;
			if (THING_ID.equals(id))
				return false;
			id = getBridiElementTypeId.apply(id);
			LogUtil.debug("id:", id);
		}
		throw new Error("Probable type loop");
	}

}
