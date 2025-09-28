package io.github.magwas.inez.query;

import io.github.magwas.inez.parse.RepresentationTestData;

public interface QueryProcessorTestData extends RepresentationTestData {
	String TEST_TEXT = SUMTI_IS_A_THING_IS_A_THING_NOREFERENCE_REPR + "\n"
			+ TAUTOLOGY_IS_A_THING_REPR + "\n" + ALICE_EATS_BANANA_REPR + "\n"
			+ ALICE_EATS_CHIPS_REPR + "\n" + BOB_EATS_BANANA_REPR + "\n"
			+ BOB_EATS_CHIPS_REPR + "\n" + CECILE_EATS_BANANA_REPR + "\n"
			+ CECILE_LOOKS_AT_BANANA_REPR + "\n" + SUMTI_IS_A_THING_REPR + "\n"
			+ TAUTOLOGY_GENERATED_REPR;

	String SAVE_CLASS_NAME = "io.github.magwas.inez.functions.Save";
}
