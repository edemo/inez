package io.github.magwas.inez.storage.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.storage.FindAllByRepresentationService;
import io.github.magwas.inez.tests.BridiTestData;
import io.github.magwas.konveyor.testing.TestBase;

class FindAllByRepresentationTest extends TestBase implements BridiTestData {

	@InjectMocks
	FindAllByRepresentationService findAllByRepresentation;

	@Test
	@DisplayName("finds all bridis based on a representation")
	void test() {
		assertEquals(Set.of(GO, GO2), findAllByRepresentation.apply(GO_REPR).collect(Collectors.toSet()));
	}
}
