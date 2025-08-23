package io.github.magwas.inez.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.testing.TestBase;


class ParseTextTest extends TestBase implements ParserOutputTestData {

	@InjectMocks
	ParseTextService parseText;

	@Test
	@DisplayName("a sumti parsed to just a top element")
	void test() {
		ParserOutput actual = parseText.apply("alice").toList().get(0);
		assertEquals(new ParserOutput("alice", Map.of()), actual);
	}

	@Test
	@DisplayName("a bridi with one sumti is parsed to the bridi representation and a map containing"
			+ "- from the representation to the list of its selbrified version and the sumti")
	void test2() {
		ParserOutput actual = parseText.apply("{alice} go").toList().get(0);
		assertEquals(new ParserOutput("{alice} go",
				Map.of("{alice} go", List.of("{0} go", "alice"))), actual);
	}

	@Test
	@DisplayName("a bridi with one reference is parsed to the bridi representation and a map containing"
			+ "- from the representation to the list of its selbrified version and the reference")
	void test3() {
		ParserOutput actual = parseText.apply("{@alice} go").toList().get(0);
		assertEquals(new ParserOutput("{@alice} go",
				Map.of("{@alice} go", List.of("{0} go", "@alice"))), actual);
	}

	@Test
	@DisplayName("a bridi with two sumties is parsed to the bridi representation and a map containing"
			+ "- from the representation to the list of its selbrified version and the sumties")
	void test4() {
		ParserOutput actual = parseText.apply(TAUTOLOGY_GENERATED_REPR).toList().get(0);
		assertEquals(OUTPUT_TAUTOLOGY, actual);
	}

	@Test
	@DisplayName("in case of unrecognized tree element, an InternalError is thrown")
	void test5() {
		assertThrows(InternalError.class,
				() -> parseText.apply(INPUT_FROM_UNKNOWN_PARSER).toList());
	}

	@Test
	@DisplayName("if the text cannot be unambigously parsed, a ParseCancellationException is thrown")
	void test1() {
		assertThrows(ParseCancellationException.class,
				() -> parseText.apply(INPUT_BAD));
	}

	@Test
	@DisplayName("literal is parsed correctly")
	void test6() {
		assertEquals(OUTPOUT_WITH_LITERAL,
				parseText.apply(INPUT_WITH_LITERAL).toList().get(0));
	}

	@Test
	@DisplayName("multiline is working")
	void test7() {
		assertEquals(List.of(OUTPOUT_WITH_LITERAL,OUTPUT_TAUTOLOGY),
				parseText.apply(INPUT_WITH_LITERAL+"\n"+TAUTOLOGY_GENERATED_REPR).toList());
	}

}
