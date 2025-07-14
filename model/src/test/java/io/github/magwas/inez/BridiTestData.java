package io.github.magwas.inez;

import java.util.List;
import java.util.Set;

import io.github.magwas.inez.storage.StorageConstants;

public interface BridiTestData extends BridiFieldTestData {

	Bridi GO1 = new Bridi("go1", GO_REPRESENTATION);
	Bridi GO2 = new Bridi("go2", GO_REPRESENTATION);
	Bridi RECURSIVE_BRIDI = new Bridi(RECURSIVE_BRIDI_REPR_NOREFERENCE,
			RECURSIVE_BRIDI_REPR_NOREFERENCE, RECURSIVE_BRIDI_REFERENCES, true);
	Bridi RECURSIVE_BRIDI_SHORTTERM = new Bridi(RECURSIVE_BRIDI_REPR_NOREFERENCE,
			RECURSIVE_BRIDI_REPR_NOREFERENCE, RECURSIVE_BRIDI_REFERENCES, false);
	Bridi THING = new Bridi(THING_REPR, THING_REPR);
	Bridi THING_CHANGED = new Bridi(THING_REPR, "thung");
	Bridi SUMTI = new Bridi(SUMTI_REP, SUMTI_REP);
	Bridi BRIDI = new Bridi(BRIDI_REPR, BRIDI_REPR);
	Bridi IS_A = new Bridi(IS_A_REPR, IS_A_REPR);
	Bridi SUMTI_IS_A_THING = new Bridi(SUMTI_IS_A_THING_REPR,
			SUMTI_IS_A_THING_REPR, List.of(IS_A_REPR, SUMTI_REP, THING_REPR), true);
	Bridi SUMTI_IS_A_THING_SHORTTERM = new Bridi(SUMTI_IS_A_THING_REPR,
			SUMTI_IS_A_THING_REPR, List.of(IS_A_REPR, SUMTI_REP, THING_REPR), false);

	Bridi QUERY_BRIDI = new Bridi(StorageConstants.QUERY_BRIDI_ID,
			StorageConstants.QUERY_BRIDI_ID);

	Bridi SIMPLE_QUERY = new Bridi(SIMPLE_QUERY_STRING, SIMPLE_QUERY_STRING,
			List.of(IS_A_REPR, StorageConstants.QUERY_BRIDI_ID, THING_REPR), true);
	Set<Bridi> SIMPLE_QUERY_OUTPUT = Set.of(RECURSIVE_BRIDI, SUMTI_IS_A_THING);

}
