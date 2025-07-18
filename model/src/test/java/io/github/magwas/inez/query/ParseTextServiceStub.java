package io.github.magwas.inez.query;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.github.magwas.inez.Inez;

class ParseTextServiceStub implements ParserOutputTestData {
	public static ParseTextService stub() {
		ParseTextService mock = mock(ParseTextService.class);
		when(mock.apply(QUERY_NONEXISTING)).thenReturn(OUTPUT_NONEXISTING);
		when(mock.apply(THING_REPR)).thenReturn(OUTPUT_THING);
		when(mock.apply(GO_REPRESENTATION)).thenReturn(OUTPUT_GO);
		when(mock.apply(SUMTI_IS_A_THING_REPR)).thenReturn(OUTPUT_SUMTI_IS_A_THING);
		when(mock.apply(GO1_REFERENCE)).thenReturn(OUTPUT_GO1);
		when(mock.apply(NONEXISTENT_REFERENCE))
				.thenReturn(OUTPUT_NONEXISTENT_REFERENCE);
		when(mock.apply(TAUTOLOGY_GENERATED_REPR)).thenReturn(OUTPUT_TAUTOLOGY);
		when(mock.apply(Inez.QUERY_BRIDI_ID)).thenReturn(OUTPUT_ANY);

		when(mock.apply(QUERY_STRING_SIMPLE)).thenReturn(OUTPUT_SIMPLE);
		when(mock.apply(QUERY_STRING_ALL_ANY)).thenReturn(OUTPUT_ALL_ANY);
		when(mock.apply(QUERY_STRING_NONMATCHING)).thenReturn(OUTPUT_NONMATCHING);
		when(mock.apply(RECURSIVE_QUERY)).thenReturn(OUTPUT_RECURSIVE);
		return mock;
	}
}
