package io.github.magwas.inez.storage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Stream;

import io.github.magwas.inez.BridiTestData;

class BridiStoreStub implements BridiTestData {
	public static BridiStore stub() {
		BridiStore mock = mock(BridiStore.class);
		when(mock.findById(IS_A_REPR)).thenReturn(Optional.of(IS_A));
		when(mock.findById(GO1_ID)).thenReturn(Optional.of(GO1));
		when(mock.findById(GOD_REPR)).thenReturn(Optional.of(GOD));
		when(mock.findById(NONEXISTENT_ID)).thenReturn(Optional.empty());
		when(mock.findById(SUMTI_IS_A_THING_REPR))
				.thenReturn(Optional.of(SUMTI_IS_A_THING));

		when(mock.findById(THING_REPR)).thenReturn(Optional.of(THING));
		when(mock.getBridiBySelbriAndSumtiIds(IS_A_REPR, THING_REPR, 1))
				.thenReturn(Stream.of(TAUTOLOGY, THING_IS_A_SUMTI));
		when(mock.getBridiBySelbriAndSumtiIds(IS_A_REPR, THING_REPR, 2))
				.thenReturn(Stream.of(TAUTOLOGY, SUMTI_IS_A_THING_IS_A_THING,
						SUMTI_IS_A_THING, TAUTOLOGY_IS_A_THING))
				.thenReturn(Stream.of(TAUTOLOGY, SUMTI_IS_A_THING_IS_A_THING,
						SUMTI_IS_A_THING, TAUTOLOGY_IS_A_THING));
		when(mock.getBridiBySelbriAndSumtiIds(IS_A_REPR, IS_A_REPR, 0))
				.thenReturn(Stream.of(SUMTI_IS_A_THING, SUMTI_IS_A_THING_IS_A_THING));
		when(mock.getBridiBySelbriAndSumtiIds(IS_A_REPR,
				RECURSIVE_BRIDI_REPR_NOREFERENCE, 1))
				.thenReturn(Stream.of(SUMTI_IS_A_THING_IS_A_THING));
		when(mock.getBridiBySelbriAndSumtiIds(IS_A_REPR, SUMTI_IS_A_THING_REPR, 1))
				.thenReturn(Stream.of(SUMTI_IS_A_THING));
		when(mock.getBridiBySelbriAndSumtiIds(IS_A_REPR, GOD_REPR, 2))
				.thenReturn(Stream.of());

		when(mock.findAllByRepresentation(THING_REPR))
				.thenAnswer((args) -> Stream.of(THING));
		when(mock.findAllByRepresentation(GO_REPRESENTATION))
				.thenAnswer((args) -> Stream.of(GO1, GO2));
		when(mock.findAllByRepresentation(SUMTI_IS_A_THING_REPR))
				.thenAnswer((args) -> Stream.of(SUMTI_IS_A_THING));
		when(mock.findAllByRepresentation(StorageConstants.QUERY_BRIDI_ID))
				.thenAnswer((args) -> Stream.of(ANY));

		return mock;
	}
}
