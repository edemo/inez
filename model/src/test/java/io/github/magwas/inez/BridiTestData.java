package io.github.magwas.inez;

import java.util.List;
import java.util.Set;

public interface BridiTestData extends BridiFieldTestData {

	String GO2_ID = "go2";
	Bridi GO1 = new Bridi(GO1_ID, GO_REPRESENTATION, null);
	Bridi GO2 = new Bridi(GO2_ID, GO_REPRESENTATION, null);
	Bridi SUMTI_IS_A_THING_IS_A_THING = new Bridi(
			SUMTI_IS_A_THING_IS_A_THING_REPR_NOREFERENCE,
			SUMTI_IS_A_THING_IS_A_THING_REPR_NOREFERENCE, RECURSIVE_BRIDI_REFERENCES);
	Bridi TAUTOLOGY_IS_A_THING = new Bridi(TAUTOLOGY_IS_A_THING_REPR,
			TAUTOLOGY_IS_A_THING_REPR, RECURSIVE_BRIDI_REFERENCES);
	Bridi THING = new Bridi(THING_ID, THING_REPR, null);
	Bridi THING_CHANGED = new Bridi(THING_ID, "thung", null);
	Bridi SUMTI = new Bridi(SUMTI_REPR, SUMTI_REPR, null);
	Bridi NONEXISTENT = new Bridi(NONEXISTENT_ID, NONEXISTENT_REPR, null);
	Bridi BRIDI = new Bridi(BRIDI_REPR, BRIDI_REPR, null);
	Bridi IS_A = new Bridi(IS_A_REPR, IS_A_REPR, null);
	Bridi ANY = new Bridi(Inez.QUERY_BRIDI_ID, Inez.QUERY_BRIDI_ID, null);
	Bridi SUMTI_IS_A_THING = new Bridi(SUMTI_IS_A_THING_ID, SUMTI_IS_A_THING_REPR,
			List.of(IS_A_REPR, SUMTI_REPR, THING_REPR));
	Bridi SUMTI_IS_A_THING_CHANGED = new Bridi(SUMTI_IS_A_THING_ID,
			SUMTI_IS_A_THING_CHANGED_REPR,
			List.of(IS_A_REPR, THING_REPR, THING_REPR));
	Bridi TAUTOLOGY = new Bridi("tautology", TAUTOLOGY_REPR,
			List.of(IS_A_REPR, THING_REPR, THING_REPR));
	Bridi THING_IS_A_SUMTI = new Bridi("not tautology", TAUTOLOGY_REPR,
			List.of(IS_A_REPR, THING_REPR, SUMTI_REPR));

	Bridi GOD = new Bridi(GOD_REPR, GOD_REPR, null);
	Set<Bridi> SIMPLE_QUERY_OUTPUT = Set.of(TAUTOLOGY,
			SUMTI_IS_A_THING_IS_A_THING, TAUTOLOGY_IS_A_THING, SUMTI_IS_A_THING);

}
