package io.github.magwas.inez;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.TestBase;

class CreateBridiFromBridiListTest extends TestBase implements BridiTestData {

	@InjectMocks
	CreateBridiFromReferences createBridiFromBridiList;

	@Test
	@DisplayName("creates a bridi from the list containing the ids of the selbri and the sumtis")
	void test3() {
		assertEquals(SUMTI_IS_A_THING_IS_A_THING,
				createBridiFromBridiList.apply(RECURSIVE_BRIDI_REFERENCES),
				"bridi differs");
	}

	@Test
	@DisplayName("if some of the referenced bridis are not in the store, a NoSuchElementException is thrown")
	void test4() {
		assertThrows(NoSuchElementException.class,
				() -> createBridiFromBridiList.apply(BAD_BRIDI_REFERENCES), "no throw");
	}

}
