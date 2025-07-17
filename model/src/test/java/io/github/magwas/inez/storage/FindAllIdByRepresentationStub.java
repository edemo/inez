package io.github.magwas.inez.storage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import io.github.magwas.inez.BridiTestData;
import io.github.magwas.inez.Inez;

public class FindAllIdByRepresentationStub implements BridiTestData {
	public static FindAllIdByRepresentation stub() {
		FindAllIdByRepresentation mock = mock(FindAllIdByRepresentation.class);
		when(mock.apply(THING_REPR)).thenAnswer((args) -> Stream.of(THING_ID));
		when(mock.apply(GO_REPRESENTATION))
				.thenAnswer((args) -> Stream.of(GO1_ID, GO2_ID));
		when(mock.apply(SUMTI_IS_A_THING_REPR))
				.thenAnswer((args) -> Stream.of(SUMTI_IS_A_THING_ID));
		when(mock.apply(Inez.QUERY_BRIDI_ID))
				.thenAnswer((args) -> Stream.of(Inez.QUERY_BRIDI_ID));
		when(mock.apply(IS_A_REPR)).thenAnswer((args) -> Stream.of(IS_A_ID));

		return mock;
	}
}
