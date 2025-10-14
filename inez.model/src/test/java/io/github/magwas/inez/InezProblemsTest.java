package io.github.magwas.inez;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.testing.TestBase;

public class InezProblemsTest extends TestBase implements InezProblemTestData {

	@InjectMocks
	InezImpl inez;

	@Test
	@DisplayName("Problem list can be obtained and queried")
	void test() {
		assertEquals(PROBLEMS, inez.getProblems().findAll());
	}
}
