package io.github.magwas.inez.parse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

class ParseTextServiceStub implements ParserOutputTestData {
	public static ParseTextService stub() {
		ParseTextService mock = mock(ParseTextService.class);
		when(mock.apply(NONEXISTENT_REPR)).thenReturn(Stream.of(OUTPUT_NONEXISTING));
		when(mock.apply(THING_REPR)).thenReturn(Stream.of(OUTPUT_THING));
		when(mock.apply(GO_REPR)).thenReturn(Stream.of(OUTPUT_GO));
		when(mock.apply(SUMTI_IS_A_THING_REPR)).thenReturn(Stream.of(OUTPUT_SUMTI_IS_A_THING));
		when(mock.apply(GO1_REFERENCE)).thenReturn(Stream.of(OUTPUT_GO1));
		when(mock.apply(NONEXISTENT_REFERENCE)).thenReturn(Stream.of(OUTPUT_NONEXISTENT_REFERENCE));
		when(mock.apply(TAUTOLOGY_GENERATED_REPR)).thenReturn(Stream.of(OUTPUT_TAUTOLOGY));
		when(mock.apply(ParserConstants.QUERY_BRIDI_ID)).thenReturn(Stream.of(OUTPUT_ANY));

		when(mock.apply(SIMPLE_INPUT)).thenReturn(Stream.of(OUTPUT_SIMPLE));
		when(mock.apply(ALL_ANY_INPUT)).thenReturn(Stream.of(OUTPUT_ALL_ANY));
		when(mock.apply(NONMATCHING_INPUT)).thenReturn(Stream.of(OUTPUT_NONMATCHING));
		when(mock.apply(RECURSIVE_INPUT)).thenReturn(Stream.of(OUTPUT_RECURSIVE));
		return mock;
	}
}
