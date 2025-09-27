package io.github.magwas.inez.storage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import io.github.magwas.inez.BridiTestData;
import io.github.magwas.inez.parse.ParserConstants;

public class FindAllIdByRepresentationServiceStub implements BridiTestData {
	public static FindAllIdByRepresentationService stub() {
		FindAllIdByRepresentationService mock = mock(FindAllIdByRepresentationService.class);
		when(mock.apply(THING_REPR)).thenAnswer((args) -> Stream.of(THING_ID));
		when(mock.apply(GO_REPRESENTATION)).thenAnswer((args) -> Stream.of(GO1_ID, GO2_ID));
		when(mock.apply(SUMTI_IS_A_THING_REPR)).thenAnswer((args) -> Stream.of(SUMTI_IS_A_THING_ID));
		when(mock.apply(ParserConstants.QUERY_BRIDI_ID))
				.thenAnswer((args) -> Stream.of(ParserConstants.QUERY_BRIDI_ID));
		when(mock.apply(IS_A_REPR)).thenAnswer((args) -> Stream.of(IS_A_ID));

		return mock;
	}
}
