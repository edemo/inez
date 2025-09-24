package io.github.magwas.inez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.BridiTestData;
import io.github.magwas.testing.TestBase;

class FindBridiByIdTest extends TestBase implements BridiTestData {

	@InjectMocks
	FindBridiByIdService findBridiById;

	@Test
	@DisplayName("finds the bridi by Id")
	void test() {
		assertEquals(Optional.of(SUMTI_IS_A_THING),
				findBridiById.apply(SUMTI_IS_A_THING_ID));
	}

	@Test
	@DisplayName("returns empty if no bridi found")
	void test1() {
		assertEquals(Optional.empty(), findBridiById.apply(NONEXISTENT_ID));
	}

}
