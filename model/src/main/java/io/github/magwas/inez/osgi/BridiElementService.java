package io.github.magwas.inez.osgi;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.element.BridiElement;
import io.github.magwas.inez.element.BridiElementSystemInitializationService;
import io.github.magwas.inez.element.ElementConstants;

@Service
public class BridiElementService {

	@Autowired
	BridiElementSystemInitializationService bridiElementSystemInitialization;

	void initialize() {
		try {
			bridiElementSystemInitialization.apply();
		} catch (IOException e) {
			throw new Error(e);
		}

	}

	public BridiElement root() {
		return BridiElement.byId(ElementConstants.ROOT_ID);
	}

}
