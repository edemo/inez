package io.github.magwas.inez.parse;

import java.util.List;

public interface BridiFieldTestData {
	String ALICE_REPR = "alice";
	String ALICE_ID = IdUtil.createID(ALICE_REPR);
	String THING_REPR = "thing";
	String THING_ID = "THING";
	String THING_REFERENCE = "@" + THING_ID;
	String SUMTI_REPR = "sumti";
	String BRIDI_REPR = "bridi";
	String IS_A_REPR = "{0} is a {1}";
	String SUMTI_IS_A_THING_REPR = "{sumti} is a {thing}";
	String SUMTI_IS_A_THING_CHANGED_REPR = SUMTI_IS_A_THING_REPR + " changed";
	String THING_IS_A_SUMTI_REPR = "{thing} is a {sumti}";
	String TAUTOLOGY_REPR = "the most basic tautology";
	String TAUTOLOGY_GENERATED_REPR = "{thing} is a {thing}";
	String SUMTI_IS_A_THING_IS_A_THING_REPR = "{{sumti} is a {thing}} is a {@thing}";
	String SUMTI_IS_A_THING_IS_A_THING_REPR_NOREFERENCE = "{{sumti} is a {thing}} is a {thing}";
	String TAUTOLOGY_IS_A_THING_REPR = "{{thing} is a {thing}} is a {thing}";
	String STUFF_ID = "stuff";
	String COULDBE_ID = "ID:could be";

	String GO_REPRESENTATION = "go";
	String GO1_ID = "go1";
	String GO2_ID = "go2";
	String GO1_REFERENCE = "@go1";
	String NONEXISTENT_REFERENCE = "@nonexistent";
	String NONEXISTENT_REPR = "nonexistent";
	String INPUT_BAD = "{bridi is a {thing}";
	String INPUT_FROM_UNKNOWN_PARSER = "INPUT_FROM_UNKNOWN_PARSER";
	String QUERY_NONEXISTING = "nonexisting";
	String QUERY_STRING_SIMPLE = "{$?} is a {thing}";
	String QUERY_STRING_ALL_ANY = "{$?} is a {$?}";
	String QUERY_STRING_NONMATCHING = "{$?} is a {god}";
	String RECURSIVE_QUERY = "{{$?} is a {thing}} is a {thing}";

	String DESCRIPTION_SUMTI_IS_A_THING_STRING = "sumti is a thing";
	String GOD_REPR = "god";
	String TWO_SUMTI_BRIDI_REPR = "{0} {1}";
	String CECILE_LOOKS_AT_BANANA = "{cecile} {{looks at} {banana}}";
	String CECILE_EATS_BANANA = "{cecile} {{eats} {banana}}";
	String BOB_EATS_CHIPS = "{bob} {{eats} {chips}}";
	String BOB_EATS_BANANA = "{bob} {{eats} {banana}}";
	String ALICE_EATS_CHIPS = "{alice} {{eats} {chips}}";
	String ALICE_EATS_BANANA = "{alice} {{eats} {banana}}";

	String TEST_TEXT = ALICE_EATS_BANANA + "\n"
			+ ALICE_EATS_CHIPS + "\n" 
			+ BOB_EATS_BANANA+ "\n"
			+ BOB_EATS_CHIPS+ "\n" 
			+ CECILE_EATS_BANANA+ "\n"
			+ CECILE_LOOKS_AT_BANANA+ "\n"
			+ SUMTI_IS_A_THING_REPR + "\n"
			+ THING_IS_A_SUMTI_REPR + "\n"
			+ TAUTOLOGY_GENERATED_REPR + "\n"
			+ SUMTI_IS_A_THING_IS_A_THING_REPR + "\n"
			+ SUMTI_IS_A_THING_IS_A_THING_REPR_NOREFERENCE + "\n"
			+ TAUTOLOGY_IS_A_THING_REPR;

	String SUMTI_ID = IdUtil.createID("sumti");
	String BRIDI_ID = IdUtil.createID("bridi");
	String IS_A_ID = "IS_A";
	String SUMTI_IS_A_THING_ID = IdUtil.createID("{sumti} is a {thing}");
	String SUMTI_IS_A_THING_IS_A_THING_ID = IdUtil
			.createID("{{sumti} is a {thing}} is a {thing}");
	String TAUTOLOGY_IS_A_THING_ID = IdUtil
			.createID("{{thing} is a {thing}} is a {thing}");
	String THING_IS_A_SUMTI_ID = "not tautology";
	String TAUTOLOGY_ID = "tautology";

	String GOD_ID = IdUtil.createID("god");
	String NOT_SAVED_ID = IdUtil.createID(SUMTI_REPR);
	String NONEXISTENT_ID = IdUtil.createID(NONEXISTENT_REPR);
	List<String> SUMTI_IS_A_THING_IS_A_THING_REFERENCES = List.of(IS_A_ID,
			SUMTI_IS_A_THING_ID, THING_ID);
	List<String> TAUTOLOGY_IS_A_THING_REFERENCES = List.of(IS_A_ID, TAUTOLOGY_ID,
			THING_REPR);
	List<String> BAD_BRIDI_REFERENCES = List.of(IS_A_REPR, SUMTI_IS_A_THING_ID,
			NONEXISTENT_REPR);
	List<String> SUMTI_IS_A_THING_CHANGED_REFERENCES = List.of(IS_A_ID, THING_ID,
			THING_ID);
	List<String> SUMTI_IS_A_THING_REFERENCES = List.of(IS_A_ID, SUMTI_ID,
			THING_ID);
	List<String> SUMTI_IS_A_THING_GENERATED_REFERENCES = List.of(IdUtil.createID(IS_A_REPR),
			SUMTI_ID, IdUtil.createID(THING_REPR));
	List<String> THING_IS_A_SUMTI_REFERENCES = List.of(IS_A_ID, THING_ID,
			SUMTI_ID);
	String INPUT_WITH_LITERAL = "{My folder} contains {[{0} is a {1}]}";
	List<String> INPUT_WITH_LITERAL_REFS = List.of("{0} contains {1}", "My folder", "{0} is a {1}");

	
}
