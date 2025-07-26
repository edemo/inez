package io.github.magwas.inez.storage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import io.github.magwas.inez.BridiTestData;
import io.github.magwas.inez.parse.ParserConstants;

public class FindAllByRepresentationServiceStub implements BridiTestData {
	public static FindAllByRepresentationService stub() {
		FindAllByRepresentationService mock = mock(
				FindAllByRepresentationService.class);
		when(mock.apply(THING_REPR)).thenAnswer((args) -> Stream.of(THING));
		when(mock.apply(GO_REPRESENTATION))
				.thenAnswer((args) -> Stream.of(GO1, GO2));
		when(mock.apply(SUMTI_IS_A_THING_REPR))
				.thenAnswer((args) -> Stream.of(SUMTI_IS_A_THING));
		when(mock.apply(ParserConstants.QUERY_BRIDI_ID))
				.thenAnswer((args) -> Stream.of(ANY));
		return mock;
	}
}
