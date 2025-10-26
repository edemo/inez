package io.github.magwas.inez.storage.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.storage.CreateBridiFromSumtiService;
import io.github.magwas.inez.storage.model.tests.SumtiTestData;
import io.github.magwas.inez.tests.BridiTestData;
import io.github.magwas.konveyor.testing.TestBase;

class CreateBridiFromSumtiTest extends TestBase implements BridiTestData, SumtiTestData {

	@InjectMocks
	CreateBridiFromSumtiService createBridiFromSumti;

	@Test
	@DisplayName("creates a bridi from the sumti")
	void test() {
		assertEquals(SUMTI_IS_A_THING, createBridiFromSumti.apply(SUMTI_IS_A_THING_SUMTI));
	}
}
