package io.github.magwas.inez.query;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.BridiTestData;
import io.github.magwas.testing.TestBase;

class CreateBridisFromParserOutputTest extends TestBase
		implements BridiTestData, ParserOutputTestData {
	@InjectMocks
	CreateBridisFromParserOutputService createBridisFromParserOutput;

	@Test
	@DisplayName("creates a list of bridis from the output of the parser\n"
			+ " - does not save them")
	void test() {
		Set<Bridi> expected = Set.of(THING_GENERATED, IS_A, SUMTI,
				SUMTI_IS_A_THING_GENERATED);
		Set<Bridi> actual = createBridisFromParserOutput
				.apply(OUTPUT_SUMTI_IS_A_THING);
		assertEquals(expected, actual);
	}
}
