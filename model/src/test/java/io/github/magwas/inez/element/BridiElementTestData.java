package io.github.magwas.inez.element;

import java.util.List;
import java.util.Set;

import io.github.magwas.inez.parse.ParserConstants;

public interface BridiElementTestData extends ElementConstants {
	String CONTAINS_ELEMENT_REPR = "{my model} contains {Alice}";
	String CONTAINS_ELEMENT_ID = ParserConstants.createID(CONTAINS_ELEMENT_REPR);
	String MY_MODEL_REPR = "my model";
	String MY_MODEL_ID = ParserConstants.createID(MY_MODEL_REPR);
	String FOLDER_ID = ParserConstants.createID("folder");
	List<String> CONTAINS_ELEMENT_REFERENCES = List.of(CONTAINS_ID, MY_MODEL_ID,
			ParserConstants.createID("Alice"));
	String HUMAN_REPR = "human";
	String HUMAN_ID = ParserConstants.createID(HUMAN_REPR);
	String ALICE_REPR = "Alice";
	String ALICE_ID = ParserConstants.createID(ALICE_REPR);
	Set<String> MY_FOLDER_CHILDREN = Set.of(ALICE_ID, HUMAN_ID, IS_A_ID);

}
