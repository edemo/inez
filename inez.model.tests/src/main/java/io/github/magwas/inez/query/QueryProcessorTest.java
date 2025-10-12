package io.github.magwas.inez.query;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;
import java.util.stream.Stream;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.parse.ParserConstants;
import io.github.magwas.runtime.LogUtil;
import io.github.magwas.testing.TestBase;
import io.github.magwas.testing.TestUtil;

class QueryProcessorTest extends TestBase implements QueryProcessorTestData {

	@InjectMocks
	QueryProcessorService queryProcessor;

	@Test
	@DisplayName("for a nonexisting sumti returns the empty list")
	void test() {
		Stream<Bridi> actual = queryProcessor.apply(NONEXISTING_INPUT);
		TestUtil.assertStreamEquals(Set.of(), actual);
	}

	@Test
	@DisplayName("for an existing sumti returns the sumti")
	void test_1() {
		TestUtil.assertStreamEquals(Set.of(THING),
				queryProcessor.apply(THING_REPR));
	}

	@Test
	@DisplayName("if more sumtis exist with the representation, return them all")
	void test_2() {
		TestUtil.assertStreamEquals(Set.of(GO1, GO2),
				queryProcessor.apply(GO_REPR));
	}

	@Test
	@DisplayName("for a reference, return the referenced bridi")
	void test_3() {
		LogUtil.addDebuggedClass(QueryProcessorService.class);
		TestUtil.assertStreamEquals(Set.of(GO1),
				queryProcessor.apply(GO1_REFERENCE));
	}

	@Test
	@DisplayName("for a reference to a nonexisting bridi, return an empty list")
	void test_5() {
		TestUtil.assertStreamEquals(Set.of(),
				queryProcessor.apply(NONEXISTENT_REFERENCE));
	}

	@Test
	@DisplayName("for a bridi with the matching representation returns it")
	void test_4() {
		TestUtil.assertStreamEquals(Set.of(SUMTI_IS_A_THING),
				queryProcessor.apply(SUMTI_IS_A_THING_REPR));
	}

	@Test
	@DisplayName("for a bridi where the representation does not match, but the selbri and sumties do, finds it")
	void test_6() {
		TestUtil.assertStreamEquals(Set.of(TAUTOLOGY),
				queryProcessor.apply(TAUTOLOGY_GENERATED_REPR));
	}

	@Test
	@DisplayName("'$?' returns a list of the any sumti")
	void test_7() {
		TestUtil.assertStreamEquals(Set.of(ANY),
				queryProcessor.apply(ParserConstants.QUERY_BRIDI_ID));
	}

	@Test
	@DisplayName("for a bridi where one of the sumties is '$?', the matching bridies return")
	void test1() {
		TestUtil.assertStreamEquals(SIMPLE_QUERY_OUTPUT,
				queryProcessor.apply(SIMPLE_INPUT));
	}

	@Test
	@DisplayName("for a bridi where all of the sumties is '$?', a ParseCancellationException is thrown")
	void test1_1() {
		assertThrows(ParseCancellationException.class,
				() -> queryProcessor.apply(ALL_ANY_INPUT).toArray());
	}

	@Test
	@DisplayName("if no stored bridi matches the query with '$?', and empty list is returned")
	void test2() {
		TestUtil.assertStreamEquals(Set.of(),
				queryProcessor.apply(NONMATCHING_INPUT));
	}

	@Test
	@DisplayName("query works with the '$?' being deep down in the query")
	void test3() {
		Stream<Bridi> actual = queryProcessor.apply(RECURSIVE_INPUT);
		TestUtil.assertStreamEquals(
				Set.of(SUMTI_IS_A_THING_IS_A_THING, TAUTOLOGY_IS_A_THING), actual);
	}
}
