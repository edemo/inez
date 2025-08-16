package io.github.magwas.inez.element;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.InezImpl;
import io.github.magwas.runtime.LogUtil;

@Service
public class BridiElementSystemInitializationService
		implements ElementConstants {
	@Autowired
	InezImpl inez;

	public void apply() throws IOException {
		inez.createSumti(IS_A_ID, IS_A_REPR);
		inez.createSumti(CONTAINS_ID, CONTAINS_REPR);
		inez.createSumti(ROOT_ID, ROOT_ID);
		inez.createSumti(THING_ID, THING_ID);
		inez.createSumti(CONTAINER_ID, CONTAINER_ID);
		inez.createSumti(RULE_ID, RULE_REPR);
		inez.createSumti(UNPLACED_ID, UNPLACED_REPR);
		String definitions = "element.definition";
		inez.createFromdefinitions(definitions).toArray();
		inez.save(List.of(
				new Bridi(ElementConstants.IS_FUNCTION_FOR, "{0} is function for {1}",
						null),
				new Bridi("io.github.magwas.inez.functions.Save",
						"io.github.magwas.inez.functions.Save", null),
				new Bridi("doSave", "doSave {0}", null),
				new Bridi("savefunctiondef",
						"{io.github.magwas.inez.functions.Save} is function for {doSave}",
						List.of(ElementConstants.IS_FUNCTION_FOR,
								"io.github.magwas.inez.functions.Save", "doSave"))));
		inez.save(List.of(new Bridi(ElementConstants.DIAGRAM_ID, "diagram", null),
				new Bridi("diagElement", "shows {0} at {1},{2}", null)));
		LogUtil.info("BridiElement system initialized");
	}

}
