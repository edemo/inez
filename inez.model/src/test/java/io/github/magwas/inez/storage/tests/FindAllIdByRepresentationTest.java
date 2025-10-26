package io.github.magwas.inez.storage.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.parse.tests.IdTestData;
import io.github.magwas.inez.storage.FindAllIdByRepresentationService;
import io.github.magwas.konveyor.testing.TestBase;

class FindAllIdByRepresentationTest extends TestBase implements IdTestData {
	@InjectMocks
	FindAllIdByRepresentationService findAllIdByRepresentation;

	@Test
	@DisplayName("returns the ids of all the sumties with the given representation")
	void test() {
		assertEquals(
				Set.of(GO_ID, GO2_ID), findAllIdByRepresentation.apply(GO_REPR).collect(Collectors.toSet()));
	}
}
