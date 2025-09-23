package io.github.magwas.inez.element;

public interface ElementConstants {
	String IS_A_ID = "IS_A";
	String IS_A_REPR = "{0} is a {1}";
	String CONTAINS_ID = "CONTAINS";
	String CONTAINS_REPR = "{0} contains {1}";
	String THING_ID = "THING";
	String ROOT_ID = "ROOT";
	String CONTAINER_ID = "CONTAINER";
	String UNPLACED_ID = "UNPLACED";
	String DIAGRAM_ID = "DIAGRAM";
	String IS_FUNCTION_FOR_ID = "IS_FUNCTION_FOR";
	String IS_FUNCTION_FOR_REPR = "{0} is function for {1}";
	String TRUE_ID = "TRUE";
	String FALSE_ID = "FALSE";
	String SAVE_FUNCTION_REF_ID = "io.github.magwas.inez.functions.Save";
	String DOSAVE_ID = "doSave";
	String DOSAVE_REPR = "doSave {0}";
	String SAVE_FUNCTION_DEF_ID = "savefunctiondef";
	String SAVE_FUNCTION_DEF_REPR = "{io.github.magwas.inez.functions.Save} is function for {doSave}";
	String DIAGRAM_ELEMENT_ID = "diagramElement";
	String DIAGRAM_ELEMENT_REPR = "shows {0} at {1},{2}";

	String ELEMENT_DEFINITIONS_RESOURCE = "element.definition";

	Integer MAX_TYPE_DEPTH = 10;
}
