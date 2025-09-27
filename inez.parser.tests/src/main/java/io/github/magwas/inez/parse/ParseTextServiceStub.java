package io.github.magwas.inez.parse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

class ParseTextServiceStub implements ParserOutputTestData {
	public static ParseTextService stub() {
		ParseTextService mock = mock(ParseTextService.class);
		when(mock.apply(QUERY_NONEXISTING)).thenReturn(Stream.of(OUTPUT_NONEXISTING));
		when(mock.apply(THING_REPR)).thenReturn(Stream.of(OUTPUT_THING));
		when(mock.apply(GO_REPRESENTATION)).thenReturn(Stream.of(OUTPUT_GO));
		when(mock.apply(SUMTI_IS_A_THING_REPR)).thenReturn(Stream.of(OUTPUT_SUMTI_IS_A_THING));
		when(mock.apply(GO1_REFERENCE)).thenReturn(Stream.of(OUTPUT_GO1));
		when(mock.apply(NONEXISTENT_REFERENCE)).thenReturn(Stream.of(OUTPUT_NONEXISTENT_REFERENCE));
		when(mock.apply(TAUTOLOGY_GENERATED_REPR)).thenReturn(Stream.of(OUTPUT_TAUTOLOGY));
		when(mock.apply(ParserConstants.QUERY_BRIDI_ID)).thenReturn(Stream.of(OUTPUT_ANY));

		when(mock.apply(QUERY_STRING_SIMPLE)).thenReturn(Stream.of(OUTPUT_SIMPLE));
		when(mock.apply(QUERY_STRING_ALL_ANY)).thenReturn(Stream.of(OUTPUT_ALL_ANY));
		when(mock.apply(QUERY_STRING_NONMATCHING)).thenReturn(Stream.of(OUTPUT_NONMATCHING));
		when(mock.apply(RECURSIVE_QUERY)).thenReturn(Stream.of(OUTPUT_RECURSIVE));
		return mock;
	}
}
