package io.github.magwas.inez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.TestBase;
import io.github.magwas.inez.BridiTestData;
import io.github.magwas.inez.storage.model.SumtiTestData;

public class CreateBridiFromSumtiTest extends TestBase
		implements BridiTestData, SumtiTestData {

	@InjectMocks
	CreateBridiFromSumti createBridiFromSumti;

	@Test
	@DisplayName("creates a bridi from the sumti")
	void test() {
		assertEquals(SUMTI_IS_A_THING,
				createBridiFromSumti.apply(SUMTI_IS_A_THING_SUMTI));
	}
}
