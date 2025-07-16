package io.github.magwas.inez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.TestBase;
import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.BridiTestData;
import io.github.magwas.inez.LogUtil;

class QueryProcessorTest extends TestBase implements BridiTestData {

	@InjectMocks
	QueryProcessor queryProcessor;

	@Test
	@DisplayName("for a nonexisting sumti returns the empty list")
	void test() {
		Set<Bridi> actual = queryProcessor.apply(QUERY_NONEXISTING);
		assertEquals(Set.of(), actual);
	}

	@Test
	@DisplayName("for an existing sumti returns the sumti")
	void test_1() {
		Set<Bridi> actual = queryProcessor.apply(THING_REPR);
		assertEquals(Set.of(THING), actual);
	}

	@Test
	@DisplayName("if more sumtis exist with the representation, return them all")
	void test_2() {
		Set<Bridi> actual = queryProcessor.apply(GO_REPRESENTATION);
		assertEquals(Set.of(GO1, GO2), actual);
	}

	@Test
	@DisplayName("for a reference, return the referenced bridi")
	void test_3() {
		Set<Bridi> actual = queryProcessor.apply(GO1_REFERENCE);
		assertEquals(Set.of(GO1), actual);
	}

	@Test
	@DisplayName("for a reference to a nonexisting bridi, return an empty list")
	void test_5() {
		Set<Bridi> actual = queryProcessor.apply(NONEXISTENT_REFERENCE);
		assertEquals(Set.of(), actual);
	}

	@Test
	@DisplayName("for a bridi with the matching representation returns it")
	void test_4() {
		Set<Bridi> actual = queryProcessor.apply(SUMTI_IS_A_THING_REPR);
		assertEquals(Set.of(SUMTI_IS_A_THING), actual);
	}

	@Test
	@DisplayName("for a bridi where the representation does not match, but the selbri and sumties do, finds it")
	void test_6() {
		Set<Bridi> actual = queryProcessor.apply(TAUTOLOGY_GENERATED_REPR);
		actual.forEach(x -> LogUtil.debug("-->" + x));
		assertEquals(Set.of(TAUTOLOGY), actual);
	}

	@Test
	@DisplayName("'$?' returns a list of the any sumti")
	void test_7() {
		Set<Bridi> actual = queryProcessor.apply(StorageConstants.QUERY_BRIDI_ID);
		assertEquals(Set.of(ANY), actual);
	}

	@Test
	@DisplayName("for a bridi where one of the sumties is '$?', the matching bridies return")
	void test1() {
		Set<Bridi> actual = queryProcessor.apply(QUERY_STRING_SIMPLE);
		actual.forEach(x -> System.out.println("-->" + x));
		assertEquals(SIMPLE_QUERY_OUTPUT, actual);
	}

	@Test
	@DisplayName("for a bridi where all of the sumties is '$?', a ParseCancellationException is thrown")
	void test1_1() {
		assertThrows(ParseCancellationException.class,
				() -> queryProcessor.apply(QUERY_STRING_ALL_ANY));
	}

	@Test
	@DisplayName("if no stored bridi matches the query with '$?', and empty list is returned")
	void test2() {
		Set<Bridi> actual = queryProcessor.apply(QUERY_STRING_NONMATCHING);
		assertEquals(Set.of(), actual);
	}

	@Test
	@DisplayName("query works with the '$?' being deep down in the query")
	void test3() {
		Set<Bridi> actual = queryProcessor.apply(RECURSIVE_QUERY);
		actual.stream().forEach(x -> LogUtil.debug("->" + x));
		assertEquals(Set.of(SUMTI_IS_A_THING_IS_A_THING, TAUTOLOGY_IS_A_THING), actual);
	}

}
