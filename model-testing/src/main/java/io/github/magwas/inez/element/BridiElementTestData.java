package io.github.magwas.inez.element;

import java.util.List;
import java.util.Set;

import io.github.magwas.inez.parse.BridiFieldTestData;
import io.github.magwas.inez.parse.IdUtil;

public interface BridiElementTestData extends BridiFieldTestData {
	String CONTAINS_ID = "CONTAINS";
	String ROOT_ID = "ROOT";
	String CONTAINER_ID = "CONTAINER";
	String CONTAINS_ELEMENT_REPR = "{my model} contains {alice}";
	String CONTAINS_ELEMENT_ID = IdUtil.createID(CONTAINS_ELEMENT_REPR);
	String MY_MODEL_REPR = "my model";
	String MY_MODEL_ID = IdUtil.createID(MY_MODEL_REPR);
	String FOLDER_ID = IdUtil.createID("folder");
	List<String> CONTAINS_ELEMENT_REFERENCES = List.of(CONTAINS_ID, MY_MODEL_ID,
			ALICE_ID);
	String HUMAN_REPR = "human";
	String HUMAN_ID = IdUtil.createID(HUMAN_REPR);
	Set<String> MY_FOLDER_CHILDREN = Set.of(ALICE_ID, HUMAN_ID, IS_A_ID);

}
