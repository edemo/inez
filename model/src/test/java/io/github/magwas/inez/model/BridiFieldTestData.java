package io.github.magwas.inez.model;

import java.util.List;

public interface BridiFieldTestData {
	String THING_REPR = "thing";
	String THING_REFERENCE = "@thing";
	String SUMTI_REP = "sumti";
	String BRIDI_REPR = "bridi";
	String IS_A_REPR = "{0} is a {1}";
	String SUMTI_IS_A_THING_REPR = "{sumti} is a {thing}";
	String THING_IS_A_SUMTI_REPR = "{thing} is a {sumti}";
	String TAUTOLOGY_REPR = "the most basic tautology";
	String TAUTOLOGY_GENERATED_REPR = "{thing} is a {thing}";
	String SUMTI_IS_A_THING_IS_A_THING_REPR = "{{sumti} is a {thing}} is a {@thing}";
	String SUMTI_IS_A_THING_IS_A_THING_REPR_NOREFERENCE = "{{sumti} is a {thing}} is a {thing}";
	String TAUTOLOGY_IS_A_THING_REPR = "{{thing} is a {thing}} is a {thing}";
	String STUFF_ID = "stuff";

	String GO_REPRESENTATION = "go";
	String GO1_ID = "go1";
	String GO1_REFERENCE = "@go1";
	String NONEXISTENT_REFERENCE = "@nonexistent";
	String NONEXISTENT_ID = "nonexistent";
	String INPUT_BAD = "{bridi is a {thing}";
	String QUERY_NONEXISTING = "nonexisting";
	String QUERY_STRING_SIMPLE = "{$?} is a {thing}";
	String QUERY_STRING_ALL_ANY = "{$?} is a {$?}";
	String QUERY_STRING_NONMATCHING = "{$?} is a {god}";
	String RECURSIVE_QUERY = "{{$?} is a {thing}} is a {thing}";

	String DESCRIPTION_SUMTI_IS_A_THING_STRING = "sumti is a thing";
	String GOD_REPR = "god";

	List<String> RECURSIVE_BRIDI_REFERENCES = List.of(IS_A_REPR,
			SUMTI_IS_A_THING_REPR, THING_REPR);
	List<String> TAUTOLOGY_IS_A_THING_REFERENCES = List.of(IS_A_REPR,
			TAUTOLOGY_GENERATED_REPR, THING_REPR);
	List<String> BAD_BRIDI_REFERENCES = List.of(IS_A_REPR, SUMTI_IS_A_THING_REPR,
			NONEXISTENT_ID);
	String TWO_SUMTI_BRIDI_REPR = "{0} {1}";
	List<String> TEST_TEXT = List.of("{alice} {{eats} {banana}}",
			"{alice} {{eats} {chips}}", "{bob} {{eats} {banana}}",
			"{bob} {{eats} {chips}}", "{cecile} {{eats} {banana}}",
			"{cecile} {{looks at} {banana}}", SUMTI_IS_A_THING_REPR,
			THING_IS_A_SUMTI_REPR, TAUTOLOGY_GENERATED_REPR,
			SUMTI_IS_A_THING_IS_A_THING_REPR,
			SUMTI_IS_A_THING_IS_A_THING_REPR_NOREFERENCE, TAUTOLOGY_IS_A_THING_REPR);
}
