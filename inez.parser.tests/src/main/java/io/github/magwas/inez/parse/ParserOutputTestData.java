package io.github.magwas.inez.parse;

import java.util.List;
import java.util.Map;

public interface ParserOutputTestData extends BridiFieldTestData {

	ParserOutput OUTPOUT_WITH_LITERAL =
			new ParserOutput(WITH_LITERAL_INPUT, Map.of(WITH_LITERAL_INPUT, INPUT_WITH_LITERAL_REFS_REFERENCES));

	ParserOutput OUTPUT_NONEXISTING = new ParserOutput(NONEXISTENT_REFERENCE, Map.of());
	ParserOutput OUTPUT_THING = new ParserOutput(THING_REPR, Map.of());
	ParserOutput OUTPUT_GO = new ParserOutput(GO_REPR, Map.of());
	ParserOutput OUTPUT_GO1 = new ParserOutput(GO1_REFERENCE, Map.of());
	ParserOutput OUTPUT_NONEXISTENT_REFERENCE = new ParserOutput(NONEXISTENT_REFERENCE, Map.of());
	ParserOutput OUTPUT_ANY = new ParserOutput(ParserConstants.QUERY_BRIDI_ID, Map.of());

	ParserOutput OUTPUT_SUMTI_IS_A_THING = new ParserOutput(
			SUMTI_IS_A_THING_REPR, Map.of(SUMTI_IS_A_THING_REPR, List.of(IS_A_REPR, SUMTI_REPR, THING_REPR)));
	ParserOutput OUTPUT_TAUTOLOGY = new ParserOutput(
			TAUTOLOGY_GENERATED_REPR, Map.of(TAUTOLOGY_GENERATED_REPR, List.of(IS_A_REPR, THING_REPR, THING_REPR)));
	ParserOutput OUTPUT_SIMPLE = new ParserOutput(
			SIMPLE_INPUT, Map.of(SIMPLE_INPUT, List.of(IS_A_REPR, ParserConstants.QUERY_BRIDI_ID, THING_REPR)));
	ParserOutput OUTPUT_ALL_ANY = new ParserOutput(
			ALL_ANY_INPUT,
			Map.of(ALL_ANY_INPUT, List.of(IS_A_REPR, ParserConstants.QUERY_BRIDI_ID, ParserConstants.QUERY_BRIDI_ID)));
	ParserOutput OUTPUT_NONMATCHING = new ParserOutput(
			NONMATCHING_INPUT, Map.of(NONMATCHING_INPUT, List.of(IS_A_REPR, ParserConstants.QUERY_BRIDI_ID, GOD_REPR)));
	ParserOutput OUTPUT_RECURSIVE = new ParserOutput(
			RECURSIVE_INPUT,
			Map.of(
					RECURSIVE_INPUT,
					List.of(IS_A_REPR, SIMPLE_INPUT, THING_REPR),
					TAUTOLOGY_GENERATED_REPR,
					List.of(IS_A_REPR, THING_REPR, THING_REPR),
					SIMPLE_INPUT,
					List.of(IS_A_REPR, ParserConstants.QUERY_BRIDI_ID, THING_REPR)));
}
