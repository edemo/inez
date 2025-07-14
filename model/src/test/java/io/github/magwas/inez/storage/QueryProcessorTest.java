package io.github.magwas.inez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.TestBase;
import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.BridiTestData;

class QueryProcessorTest extends TestBase implements BridiTestData {

	@InjectMocks
	QueryProcessor queryProcessor;

	@Test
	@DisplayName("query returns the list of bridis ")
	void test1() {
		Set<Bridi> actual = queryProcessor.apply(SIMPLE_QUERY_STRING);
		assertEquals(SIMPLE_QUERY_OUTPUT, actual);
	}

}
