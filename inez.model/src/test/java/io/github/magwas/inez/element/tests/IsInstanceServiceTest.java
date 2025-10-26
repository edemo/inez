package io.github.magwas.inez.element.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.element.IsInstanceService;
import io.github.magwas.konveyor.testing.TestBase;

class IsInstanceServiceTest extends TestBase implements BridiElementTestData {

	@InjectMocks
	IsInstanceService isInstance;

	@Test
	@DisplayName("for noninstance returns false")
	void test() {
		assertFalse(isInstance.apply(HUMAN_ID, CONTAINER_ID));
	}

	@Test
	@DisplayName("for direct instance returns true")
	void test1() {
		assertTrue(isInstance.apply(ROOT_ID, CONTAINER_ID));
	}

	@Test
	@DisplayName("for indirect instance returns true")
	void test2() {
		assertTrue(isInstance.apply(MY_MODEL_ID, CONTAINER_ID));
	}

	@Test
	@DisplayName("everything is a thing")
	void test3() {
		assertTrue(isInstance.apply(MY_MODEL_ID, THING_ID));
	}
}
