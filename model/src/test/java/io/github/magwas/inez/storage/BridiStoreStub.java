package io.github.magwas.inez.storage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Stream;

import io.github.magwas.inez.model.BridiTestData;

class BridiStoreStub implements BridiTestData {
	public static BridiStore stub() {
		BridiStore mock = mock(BridiStore.class);
		when(mock.findById(GO1_ID)).thenReturn(Optional.of(GO1));
		when(mock.findById(GO_REPRESENTATION)).thenReturn(Optional.of(GO2));
		when(mock.findById(SUMTI_IS_A_THING_IS_A_THING_REPR_NOREFERENCE))
				.thenReturn(Optional.of(SUMTI_IS_A_THING_IS_A_THING));
		when(mock.findById(TAUTOLOGY_IS_A_THING_REPR))
				.thenReturn(Optional.of(TAUTOLOGY_IS_A_THING));
		when(mock.findById(THING_REPR)).thenReturn(Optional.of(THING));
		when(mock.findById("thung")).thenReturn(Optional.of(THING_CHANGED));
		when(mock.findById(SUMTI_REP)).thenReturn(Optional.of(SUMTI));
		when(mock.findById(BRIDI_REPR)).thenReturn(Optional.of(BRIDI));
		when(mock.findById(IS_A_REPR)).thenReturn(Optional.of(IS_A));
		when(mock.findById(StorageConstants.QUERY_BRIDI_ID))
				.thenReturn(Optional.of(ANY));
		when(mock.findById(SUMTI_IS_A_THING_REPR))
				.thenReturn(Optional.of(SUMTI_IS_A_THING));
		when(mock.findById(TAUTOLOGY_GENERATED_REPR))
				.thenReturn(Optional.of(TAUTOLOGY));
		when(mock.findById(THING_IS_A_SUMTI_REPR))
				.thenReturn(Optional.of(THING_IS_A_SUMTI));
		when(mock.findById(GOD_REPR)).thenReturn(Optional.of(GOD));

		when(mock.getBridiIdBySelbriAndSumtiIds(IS_A_REPR, THING_REPR, 1))
				.thenAnswer((args) -> Stream.of(TAUTOLOGY_GENERATED_REPR,
						THING_IS_A_SUMTI_REPR));
		when(mock.getBridiIdBySelbriAndSumtiIds(IS_A_REPR, THING_REPR, 2))
				.thenAnswer((args) -> Stream.of(TAUTOLOGY_GENERATED_REPR,
						SUMTI_IS_A_THING_IS_A_THING_REPR_NOREFERENCE, SUMTI_IS_A_THING_REPR,
						TAUTOLOGY_IS_A_THING_REPR));
		when(mock.getBridiIdBySelbriAndSumtiIds(IS_A_REPR, IS_A_REPR, 0))
				.thenAnswer((args) -> Stream.of(SUMTI_IS_A_THING_REPR,
						SUMTI_IS_A_THING_IS_A_THING_REPR));
		when(mock.getBridiIdBySelbriAndSumtiIds(IS_A_REPR,
				SUMTI_IS_A_THING_IS_A_THING_REPR_NOREFERENCE, 1))
				.thenAnswer((args) -> Stream.of(SUMTI_IS_A_THING_IS_A_THING_REPR));
		when(
				mock.getBridiIdBySelbriAndSumtiIds(IS_A_REPR, SUMTI_IS_A_THING_REPR, 1))
				.thenAnswer((args) -> Stream.of(SUMTI_IS_A_THING_REPR));
		when(mock.getBridiIdBySelbriAndSumtiIds(IS_A_REPR, GOD_REPR, 2))
				.thenAnswer((args) -> Stream.of());
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
