package io.github.magwas.inez.storage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import io.github.magwas.inez.BridiTestData;

class BridiStoreStub implements BridiTestData {
	public static BridiStore stub() {
		BridiStore mock = mock(BridiStore.class);
		when(mock.findById(IS_A_REPR)).thenReturn(Optional.of(IS_A));
		when(mock.findById(SUMTI_IS_A_THING_REPR))
				.thenReturn(Optional.of(SUMTI_IS_A_THING));
		when(mock.findById(THING_REPR)).thenReturn(Optional.of(THING));
		when(mock.getBridiBySelbriAndSumtiIds(IS_A_REPR, THING_REPR, 2))
				.thenReturn(SIMPLE_QUERY_OUTPUT);
		when(mock.getBridiBySelbriAndSumtiIds(IS_A_REPR, IS_A_REPR, 0))
				.thenReturn(List.of(SUMTI_IS_A_THING, RECURSIVE_BRIDI));
		return mock;
	}
}
