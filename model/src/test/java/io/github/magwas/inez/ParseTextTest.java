package io.github.magwas.inez;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.List;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.TestBase;

class ParseTextTest extends TestBase implements BridiTestData {

	@InjectMocks
	ParseText parseText;

	@Test
	@DisplayName("returns a list of bridies from parsing the text\n"
			+ "- it contains all the bridis and sumtis parseable from the text once\n"
			+ "- the first element is the top bridi")
	void test() throws ParseCancellationException {
		List<Bridi> actual = parseText.apply(RECURSIVE_BRIDI_REPR);
		HashSet<Bridi> expectedSet = new HashSet<Bridi>(PARSED_INPUT);
		HashSet<Bridi> actualSet = new HashSet<Bridi>(actual);
		assertEquals(expectedSet, actualSet);
		assertEquals(expectedSet.size(), actual.size());
		assertEquals(RECURSIVE_BRIDI, actual.get(0));
	}

	@Test
	@DisplayName("if the text cannot be unambigously parsed, a ParseCancellationException is thrown")
	void test1() throws ParseCancellationException {
		assertThrows(ParseCancellationException.class,
				() -> parseText.apply(INPUT_BAD));
	}

}
