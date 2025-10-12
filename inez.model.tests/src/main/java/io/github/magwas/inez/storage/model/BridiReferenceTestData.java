package io.github.magwas.inez.storage.model;

import java.util.List;

import io.github.magwas.inez.parse.IdTestData;

public interface BridiReferenceTestData extends IdTestData {

	String ID = "id:";
	BridiReference TAUTOLOGY_REFERENCE_0 = new BridiReference(TAUTOLOGY_ID + 0, TAUTOLOGY_ID, IS_A_ID, 0, IS_A_ID);
	BridiReference TAUTOLOGY_REFERENCE_1 = new BridiReference(TAUTOLOGY_ID + 1, TAUTOLOGY_ID, IS_A_ID, 1, THING_ID);
	BridiReference TAUTOLOGY_REFERENCE_2 = new BridiReference(TAUTOLOGY_ID + 2, TAUTOLOGY_ID, IS_A_ID, 2, THING_ID);
	BridiReference SUMTI_IS_A_THING_REFERENCE_0 =
			new BridiReference(ID + SUMTI_IS_A_THING_ID + 0, SUMTI_IS_A_THING_ID, IS_A_ID, 0, IS_A_ID);
	BridiReference SUMTI_IS_A_THING_REFERENCE_1 =
			new BridiReference(ID + SUMTI_IS_A_THING_ID + 1, SUMTI_IS_A_THING_ID, IS_A_ID, 1, SUMTI_ID);
	BridiReference SUMTI_IS_A_THING_REFERENCE_2 =
			new BridiReference(ID + SUMTI_IS_A_THING_ID + 2, SUMTI_IS_A_THING_ID, IS_A_ID, 2, THING_ID);
	List<BridiReference> SUMTI_IS_A_THING_REFERENCELIST =
			List.of(SUMTI_IS_A_THING_REFERENCE_0, SUMTI_IS_A_THING_REFERENCE_1, SUMTI_IS_A_THING_REFERENCE_2);
	BridiReference SUMTI_IS_A_THING_IS_A_THING_REFERENCE_0 =
			new BridiReference(SUMTI_IS_A_THING_IS_A_THING_ID + 0, SUMTI_IS_A_THING_IS_A_THING_ID, IS_A_ID, 0, IS_A_ID);
	BridiReference SUMTI_IS_A_THING_IS_A_THING_REFERENCE_1 = new BridiReference(
			SUMTI_IS_A_THING_IS_A_THING_ID + 1, SUMTI_IS_A_THING_IS_A_THING_ID, IS_A_ID, 1, SUMTI_ID);
	BridiReference SUMTI_IS_A_THING_IS_A_THING_REFERENCE_2 = new BridiReference(
			SUMTI_IS_A_THING_IS_A_THING_ID + 2, SUMTI_IS_A_THING_IS_A_THING_ID, IS_A_ID, 2, THING_ID);
	BridiReference TAUTOLOGY_IS_A_THING_REFERENCE_0 =
			new BridiReference(TAUTOLOGY_IS_A_THING_ID + 1, TAUTOLOGY_IS_A_THING_ID, IS_A_ID, 1, IS_A_ID);
	BridiReference TAUTOLOGY_IS_A_THING_REFERENCE_1 =
			new BridiReference(TAUTOLOGY_IS_A_THING_ID + 1, TAUTOLOGY_IS_A_THING_ID, IS_A_ID, 1, THING_ID);
	BridiReference TAUTOLOGY_IS_A_THING_REFERENCE_2 =
			new BridiReference(TAUTOLOGY_IS_A_THING_ID + 2, TAUTOLOGY_IS_A_THING_ID, IS_A_ID, 2, THING_ID);
	BridiReference THING_IS_A_SUMTI_REFERENCE_0 =
			new BridiReference(THING_IS_A_SUMTI_ID + 0, THING_IS_A_SUMTI_ID, IS_A_ID, 0, IS_A_ID);
	BridiReference THING_IS_A_SUMTI_REFERENCE_1 =
			new BridiReference(THING_IS_A_SUMTI_ID + 1, THING_IS_A_SUMTI_ID, IS_A_ID, 1, THING_ID);
	BridiReference THING_IS_A_SUMTI_REFERENCE_2 =
			new BridiReference(THING_IS_A_SUMTI_ID + 2, THING_IS_A_SUMTI_ID, IS_A_ID, 2, SUMTI_ID);

	BridiReference STUFF_COULDBE_THING_REFERENCE_2 =
			new BridiReference(STUFF_ID + 2, COULDBE_ID, COULDBE_ID, 2, THING_ID);
}
