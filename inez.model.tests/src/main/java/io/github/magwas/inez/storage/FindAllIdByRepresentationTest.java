package io.github.magwas.inez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.parse.BridiFieldTestData;
import io.github.magwas.testing.TestBase;

class FindAllIdByRepresentationTest extends TestBase implements BridiFieldTestData {
	@InjectMocks
	FindAllIdByRepresentationService findAllIdByRepresentation;

	@Test
	@DisplayName("returns the ids of all the sumties with the given representation")
	void test() {
		assertEquals(
				Set.of(GO1_ID, GO2_ID),
				findAllIdByRepresentation.apply(GO_REPRESENTATION).collect(Collectors.toSet()));
	}
}
