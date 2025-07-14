package io.github.magwas.inez;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.TestBase;

class ParseTextTest extends TestBase implements BridiSetTestData {

	@InjectMocks
	ParseText parseText;

	@Test
	@DisplayName("returns a resultSet parsing the query")
	void test() {
		BridiSet actual = parseText.apply(RECURSIVE_BRIDI_REPR);
		assertEquals(PARSED_INPUT, actual, "set differs");
	}

	@Test
	@DisplayName("if the text cannot be unambigously parsed, a ParseCancellationException is thrown")
	void test1() {
		assertThrows(ParseCancellationException.class,
				() -> parseText.apply(INPUT_BAD));
	}

}
