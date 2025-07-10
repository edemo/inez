package io.github.magwas.inez;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.TestBase;

public class CreateBridiFromBridiListTest extends TestBase
		implements BridiTestData {

	@InjectMocks
	CreateBridiFromBridiList createBridiFromBridiList;

	@Test
	@DisplayName("creates a bridi from the list containing the ids of the selbri and the sumtis")
	void test3() {
		assertEquals(RECURSIVE_BRIDI,
				createBridiFromBridiList.apply(RECURSIVE_BRIDI_REFERENCES));
	}

}
