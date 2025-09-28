package io.github.magwas.inez.storage.model;

import io.github.magwas.inez.parse.BridiFieldTestData;

public interface SumtiTestData extends BridiFieldTestData {

	Sumti THING_SUMTI = new Sumti(THING_ID, THING_REPR);
	Sumti SUMTI_IS_A_THING_SUMTI = new Sumti(SUMTI_IS_A_THING_ID, SUMTI_IS_A_THING_REPR);
	Sumti SUMTI_IS_A_THING_CHANGED_SUMTI = new Sumti(SUMTI_IS_A_THING_ID, SUMTI_IS_A_THING_CHANGED_REPR);
	Sumti GO1_SUMTI = new Sumti(GO1_ID, GO_REPRESENTATION);
	Sumti GO2_SUMTI = new Sumti(GO2_ID, GO_REPRESENTATION);
}
