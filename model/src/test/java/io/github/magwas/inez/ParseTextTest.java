package io.github.magwas.inez;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.TestBase;

class ParseTextTest extends TestBase implements BridiTestData {

	@InjectMocks
	ParseText parseText;

	@Test
	@DisplayName("returns a list of bridies from parsing the text")
	void test() {
		List<Bridi> actual = parseText.apply(RECURSIVE_BRIDI_REPR);
		Set<Bridi> expectedSet = new HashSet<>(PARSED_INPUT);
		Set<Bridi> actualSet = new HashSet<>(actual);
		assertEquals(expectedSet, actualSet, "set differs");
	}

	@Test
	@DisplayName("- it contains all the bridis and sumtis parseable from the text once\n"
			+ "- the first element is the top bridi")
	void test_1() {
		List<Bridi> actual = parseText.apply(RECURSIVE_BRIDI_REPR);
		Set<Bridi> expectedSet = new HashSet<>(PARSED_INPUT);
		assertEquals(expectedSet.size(), actual.size(), "size differs");
	}

	@Test
	@DisplayName("- the first element is the top bridi")
	void test_2() {
		List<Bridi> actual = parseText.apply(RECURSIVE_BRIDI_REPR);
		assertEquals(RECURSIVE_BRIDI, actual.get(0), "first one is not top");
	}

	@Test
	@DisplayName("if the text cannot be unambigously parsed, a ParseCancellationException is thrown")
	void test1() {
		assertThrows(ParseCancellationException.class,
				() -> parseText.apply(INPUT_BAD));
	}

}
