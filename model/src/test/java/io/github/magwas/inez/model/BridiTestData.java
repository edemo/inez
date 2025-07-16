package io.github.magwas.inez.model;

import java.util.List;
import java.util.Set;

import io.github.magwas.inez.model.Bridi;
import io.github.magwas.inez.storage.StorageConstants;

public interface BridiTestData extends BridiFieldTestData {

	String GO2_ID = "go2";
	Bridi GO1 = new Bridi(GO1_ID, GO_REPRESENTATION, null);
	Bridi GO2 = new Bridi(GO2_ID, GO_REPRESENTATION, null);
	Bridi SUMTI_IS_A_THING_IS_A_THING = new Bridi(
			SUMTI_IS_A_THING_IS_A_THING_REPR_NOREFERENCE,
			SUMTI_IS_A_THING_IS_A_THING_REPR_NOREFERENCE, RECURSIVE_BRIDI_REFERENCES);
	Bridi TAUTOLOGY_IS_A_THING = new Bridi(TAUTOLOGY_IS_A_THING_REPR,
			TAUTOLOGY_IS_A_THING_REPR, RECURSIVE_BRIDI_REFERENCES);
	Bridi THING = new Bridi(THING_REPR, THING_REPR, null);
	Bridi THING_CHANGED = new Bridi(THING_REPR, "thung", null);
	Bridi SUMTI = new Bridi(SUMTI_REP, SUMTI_REP, null);
	Bridi BRIDI = new Bridi(BRIDI_REPR, BRIDI_REPR, null);
	Bridi IS_A = new Bridi(IS_A_REPR, IS_A_REPR, null);
	Bridi ANY = new Bridi(StorageConstants.QUERY_BRIDI_ID,
			StorageConstants.QUERY_BRIDI_ID, null);
	Bridi SUMTI_IS_A_THING = new Bridi(SUMTI_IS_A_THING_REPR,
			SUMTI_IS_A_THING_REPR, List.of(IS_A_REPR, SUMTI_REP, THING_REPR));
	Bridi TAUTOLOGY = new Bridi("tautology", TAUTOLOGY_REPR,
			List.of(IS_A_REPR, THING_REPR, THING_REPR));
	Bridi THING_IS_A_SUMTI = new Bridi("not tautology", TAUTOLOGY_REPR,
			List.of(IS_A_REPR, THING_REPR, SUMTI_REP));

	Bridi GOD = new Bridi(GOD_REPR, GOD_REPR, null);
	Set<Bridi> SIMPLE_QUERY_OUTPUT = Set.of(TAUTOLOGY,
			SUMTI_IS_A_THING_IS_A_THING, TAUTOLOGY_IS_A_THING, SUMTI_IS_A_THING);

}
