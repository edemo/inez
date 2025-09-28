package io.github.magwas.inez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.parse.IdTestData;
import io.github.magwas.testing.TestBase;

class FindAllIdByRepresentationTest extends TestBase implements IdTestData {
	@InjectMocks
	FindAllIdByRepresentationService findAllIdByRepresentation;

	@Test
	@DisplayName("returns the ids of all the sumties with the given representation")
	void test() {
		assertEquals(Set.of(GO_ID, GO2_ID),
				findAllIdByRepresentation.apply(GO_REPR).collect(Collectors.toSet()));
	}
}
