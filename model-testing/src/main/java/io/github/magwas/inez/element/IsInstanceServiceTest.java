package io.github.magwas.inez.element;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.testing.TestBase;

public class IsInstanceServiceTest extends TestBase
		implements BridiElementTestData {

	@InjectMocks
	IsInstanceService isInstance;

	@Test
	@DisplayName("for noninstance returns false")
	void test() {
		assertEquals(false, isInstance.apply(HUMAN_ID, CONTAINER_ID));
	}

	@Test
	@DisplayName("for direct instance returns true")
	void test1() {
		assertEquals(true, isInstance.apply(ROOT_ID, CONTAINER_ID));
	}

	@Test
	@DisplayName("for indirect instance returns true")
	void test2() {
		assertEquals(true, isInstance.apply(MY_MODEL_ID, CONTAINER_ID));
	}

	@Test
	@DisplayName("everything is a thing")
	void test3() {
		assertEquals(true, isInstance.apply(MY_MODEL_ID, THING_ID));
	}

}
