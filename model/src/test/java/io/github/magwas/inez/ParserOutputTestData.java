package io.github.magwas.inez;

import java.util.List;
import java.util.Map;

import io.github.magwas.inez.storage.StorageConstants;

public interface ParserOutputTestData extends BridiFieldTestData {

	ParserOutput OUTPUT_NONEXISTING = new ParserOutput(QUERY_NONEXISTING);
	ParserOutput OUTPUT_THING = new ParserOutput(THING_REPR);
	ParserOutput OUTPUT_GO = new ParserOutput(GO_REPRESENTATION);
	ParserOutput OUTPUT_GO1 = new ParserOutput(GO1_REFERENCE);
	ParserOutput OUTPUT_NONEXISTENT_REFERENCE = new ParserOutput(
			NONEXISTENT_REFERENCE);
	ParserOutput OUTPUT_ANY = new ParserOutput(StorageConstants.QUERY_BRIDI_ID);

	ParserOutput OUTPUT_SUMTI_IS_A_THING = new ParserOutput(SUMTI_IS_A_THING_REPR,
			Map.of(SUMTI_IS_A_THING_REPR, List.of(SUMTI_IS_A_THING_REPR)));
	ParserOutput OUTPUT_TAUTOLOGY = new ParserOutput(TAUTOLOGY_GENERATED_REPR,
			Map.of(TAUTOLOGY_GENERATED_REPR,
					List.of(IS_A_REPR, THING_REPR, THING_REPR)));
	ParserOutput OUTPUT_SIMPLE = new ParserOutput(QUERY_STRING_SIMPLE,
			Map.of(QUERY_STRING_SIMPLE,
					List.of(IS_A_REPR, StorageConstants.QUERY_BRIDI_ID, THING_REPR)));
	ParserOutput OUTPUT_ALL_ANY = new ParserOutput(QUERY_STRING_ALL_ANY,
			Map.of(QUERY_STRING_ALL_ANY, List.of(IS_A_REPR,
					StorageConstants.QUERY_BRIDI_ID, StorageConstants.QUERY_BRIDI_ID)));
	ParserOutput OUTPUT_NONMATCHING = new ParserOutput(QUERY_STRING_NONMATCHING,
			Map.of(QUERY_STRING_NONMATCHING,
					List.of(IS_A_REPR, StorageConstants.QUERY_BRIDI_ID, GOD_REPR)));
	ParserOutput OUTPUT_RECURSIVE = new ParserOutput(RECURSIVE_QUERY,
			Map.of(RECURSIVE_QUERY,
					List.of(IS_A_REPR, QUERY_STRING_SIMPLE, THING_REPR),
					TAUTOLOGY_GENERATED_REPR, List.of(IS_A_REPR, THING_REPR, THING_REPR),
					QUERY_STRING_SIMPLE,
					List.of(IS_A_REPR, StorageConstants.QUERY_BRIDI_ID, THING_REPR)));

}
