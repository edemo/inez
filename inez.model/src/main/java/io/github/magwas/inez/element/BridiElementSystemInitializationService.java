package io.github.magwas.inez.element;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.InezImpl;
import io.github.magwas.konveyor.runtime.LoggerService;

@Service
public class BridiElementSystemInitializationService implements ElementConstants {
	@Autowired
	InezImpl inez;

	@Autowired
	LoggerService logger;

	public void apply() throws IOException {
		inez.createSumti(ROOT_ID, ROOT_ID);
		inez.createSumti(UNPLACED_ID, UNPLACED_ID);
		inez.createSumti(IS_A_ID, IS_A_REPR);
		inez.createSumti(CONTAINS_ID, CONTAINS_REPR);
		inez.createSumti(THING_ID, THING_ID);
		inez.createSumti(CONTAINER_ID, CONTAINER_ID);
		inez.createSumti(TRUE_ID, TRUE_ID);
		inez.createSumti(FALSE_ID, FALSE_ID);
		inez.createSumti(IS_FUNCTION_FOR_ID, IS_FUNCTION_FOR_REPR);
		inez.createSumti(SAVE_FUNCTION_REF_ID, SAVE_FUNCTION_REF_ID);
		inez.createSumti(DOSAVE_ID, DOSAVE_REPR);
		inez.createSumti(DIAGRAM_ID, DIAGRAM_ID);
		inez.createSumti(DIAGRAM_ELEMENT_ID, DIAGRAM_ELEMENT_REPR);
		inez.createFromdefinitions(ELEMENT_DEFINITIONS_RESOURCE);
		logger.info("BridiElement system initialized");
	}
}
