package io.github.magwas.inez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.BridiTestData;
import io.github.magwas.testing.TestBase;

class GetBridiIdBySelbriAndSumtiIdsTest extends TestBase implements BridiTestData {

	@InjectMocks
	GetBridiIdBySelbriAndSumtiIdsService getBridiIdBySelbriAndSumtiIds;

	@Test
	@DisplayName(
			"returns the ids of all bridies with the given sumti," + " and with the given selbri in the given position")
	void test() {
		assertEquals(
				Stream.of(TAUTOLOGY_ID, SUMTI_IS_A_THING_IS_A_THING_ID, SUMTI_IS_A_THING_ID, TAUTOLOGY_IS_A_THING_ID)
						.collect(Collectors.toSet()),
				getBridiIdBySelbriAndSumtiIds.apply(IS_A_ID, THING_ID, 2).collect(Collectors.toSet()));
	}
}
