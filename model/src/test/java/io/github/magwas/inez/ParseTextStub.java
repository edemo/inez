package io.github.magwas.inez;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParseTextStub implements BridiSetTestData {
	public static ParseText stub() {
		ParseText mock = mock(ParseText.class);
		when(mock.apply(RECURSIVE_BRIDI_REPR)).thenReturn(PARSED_INPUT);
		when(mock.apply(SIMPLE_QUERY_STRING)).thenReturn(PARSED_SIMPLE_QUERY);
		return mock;
	}
}
