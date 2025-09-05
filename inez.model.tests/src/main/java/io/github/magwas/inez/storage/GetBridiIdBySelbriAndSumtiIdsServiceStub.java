package io.github.magwas.inez.storage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import io.github.magwas.inez.parse.BridiFieldTestData;

public class GetBridiIdBySelbriAndSumtiIdsServiceStub
		implements BridiFieldTestData {
	public static GetBridiIdBySelbriAndSumtiIdsService stub() {
		GetBridiIdBySelbriAndSumtiIdsService mock = mock(
				GetBridiIdBySelbriAndSumtiIdsService.class);
		when(mock.apply(IS_A_ID, THING_ID, 1))
				.thenAnswer((args) -> Stream.of(TAUTOLOGY_ID, THING_IS_A_SUMTI_ID));
		when(mock.apply(IS_A_ID, THING_ID, 2)).thenAnswer(
				(args) -> Stream.of(TAUTOLOGY_ID, SUMTI_IS_A_THING_IS_A_THING_ID,
						SUMTI_IS_A_THING_ID, TAUTOLOGY_IS_A_THING_ID));
		when(mock.apply(IS_A_ID, IS_A_ID, 0)).thenAnswer((args) -> Stream
				.of(SUMTI_IS_A_THING_ID, SUMTI_IS_A_THING_IS_A_THING_ID));
		when(mock.apply(IS_A_ID, SUMTI_IS_A_THING_IS_A_THING_ID, 1))
				.thenAnswer((args) -> Stream.of(SUMTI_IS_A_THING_IS_A_THING_ID));
		when(mock.apply(IS_A_ID, SUMTI_IS_A_THING_ID, 1))
				.thenAnswer((args) -> Stream.of(SUMTI_IS_A_THING_ID));
		when(mock.apply(IS_A_ID, GOD_ID, 2)).thenAnswer((args) -> Stream.of());

		return mock;
	}
}
