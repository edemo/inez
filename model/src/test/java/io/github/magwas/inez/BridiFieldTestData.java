package io.github.magwas.inez;

import java.util.List;

public interface BridiFieldTestData {
	String THING_REPR = "thing";
	String SUMTI_REP = "sumti";
	String BRIDI_REPR = "bridi";
	String IS_A_REPR = "{0} is a {1}";
	String SUMTI_IS_A_THING_REPR = "{sumti} is a {thing}";
	String RECURSIVE_BRIDI_REPR = "{{sumti} is a {thing}} is a {@thing}";
	String RECURSIVE_BRIDI_REPR_NOREFERENCE = "{{sumti} is a {thing}} is a {thing}";
	String INPUT_BAD = "{bridi is a {thing}";
	String STUFF_ID = "stuff";

	String GO_REPRESENTATION = "go";

	String DESCRIPTION_SUMTI_IS_A_THING_STRING = "sumti is a thing";
	String SIMPLE_QUERY_STRING = "{$?} is a {thing}";
	List<String> RECURSIVE_BRIDI_REFERENCES = List.of(IS_A_REPR,
			SUMTI_IS_A_THING_REPR, THING_REPR);
	List<String> BAD_BRIDI_REFERENCES = List.of(IS_A_REPR, SUMTI_IS_A_THING_REPR,
			BRIDI_REPR);

}
