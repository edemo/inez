package io.github.magwas.inez.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetBridiElementTypeService {
	@Autowired
	GetBridiElementTypeIdService getBridiElementTypeId;
	@Autowired
	BridiElementFactory bridiElementFactory;

	public BridiElement apply(String id) {
		String type = getBridiElementTypeId.apply(id);
		return bridiElementFactory.apply(type);
	}

}