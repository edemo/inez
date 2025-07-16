package io.github.magwas.inez;

import java.util.List;
import java.util.Set;

import io.github.magwas.inez.storage.StorageConstants;

public interface BridiTestData extends BridiFieldTestData {

	Bridi GO1 = new Bridi(GO1_ID, GO_REPRESENTATION);
	Bridi GO2 = new Bridi("go2", GO_REPRESENTATION);
	Bridi SUMTI_IS_A_THING_IS_A_THING = new Bridi(
			RECURSIVE_BRIDI_REPR_NOREFERENCE, RECURSIVE_BRIDI_REPR_NOREFERENCE,
			RECURSIVE_BRIDI_REFERENCES);
	Bridi TAUTOLOGY_IS_A_THING = new Bridi(TAUTOLOGY_IS_A_THING_REPR,
			TAUTOLOGY_IS_A_THING_REPR, RECURSIVE_BRIDI_REFERENCES);
	Bridi THING = new Bridi(THING_REPR, THING_REPR);
	Bridi THING_CHANGED = new Bridi(THING_REPR, "thung");
	Bridi SUMTI = new Bridi(SUMTI_REP, SUMTI_REP);
	Bridi BRIDI = new Bridi(BRIDI_REPR, BRIDI_REPR);
	Bridi IS_A = new Bridi(IS_A_REPR, IS_A_REPR);
	Bridi ANY = new Bridi(StorageConstants.QUERY_BRIDI_ID,
			StorageConstants.QUERY_BRIDI_ID);
	Bridi SUMTI_IS_A_THING = new Bridi(SUMTI_IS_A_THING_REPR,
			SUMTI_IS_A_THING_REPR, List.of(IS_A_REPR, SUMTI_REP, THING_REPR));
	Bridi TAUTOLOGY = new Bridi("tautology", TAUTOLOGY_REPR,
			List.of(IS_A_REPR, THING_REPR, THING_REPR));
	Bridi THING_IS_A_SUMTI = new Bridi("not tautology", TAUTOLOGY_REPR,
			List.of(IS_A_REPR, THING_REPR, SUMTI_REP));

	Bridi QUERY_BRIDI = new Bridi(StorageConstants.QUERY_BRIDI_ID,
			StorageConstants.QUERY_BRIDI_ID);

	Bridi GOD = new Bridi(GOD_REPR, GOD_REPR);
	Set<Bridi> SIMPLE_QUERY_OUTPUT = Set.of(TAUTOLOGY,
			SUMTI_IS_A_THING_IS_A_THING, TAUTOLOGY_IS_A_THING, SUMTI_IS_A_THING);

}
