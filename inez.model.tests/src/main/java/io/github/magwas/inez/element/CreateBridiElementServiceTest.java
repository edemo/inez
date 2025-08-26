package io.github.magwas.inez.element;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.text.MessageFormat;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;

import io.github.magwas.inez.Bridi;
import io.github.magwas.testing.TestBase;

public class CreateBridiElementServiceTest extends TestBase
		implements BridiElementTestData {

	@InjectMocks
	CreateBridiElementService createBridiElement;
	private BridiElement element;
	private List<Bridi> saved;

	@BeforeEach
	@Override
	public void setUp() {
		super.setUp();
		element = createBridiElement.apply(MY_MODEL_ID, HUMAN_ID, ALICE_REPR);
		@SuppressWarnings("unchecked")
		ArgumentCaptor<List<Bridi>> argument = ArgumentCaptor.forClass(List.class);
		verify(createBridiElement.saveBridi).apply(argument.capture());
		saved = argument.getValue();
	}

	@Test
	@DisplayName("saves 3 bridis")
	void test0() {
		assertEquals(3, saved.size());
	}

	@Test
	@DisplayName("the first one is the element")
	void test1() {
		String elementId = element.id;
		assertEquals(elementId, saved.get(0).id());
	}

	@Test
	@DisplayName("with the given representation")
	void test2() {
		assertEquals(ALICE_REPR, saved.get(0).representation());
	}

	@Test
	@DisplayName("and the given rerferences")
	void test3() {
		assertEquals(List.of(), saved.get(0).references());
	}

	@Test
	@DisplayName("the second one is '{@elementId} is a {@typeId}' ")
	void test4() {
		assertEquals(List.of(IS_A_ID, element.id, HUMAN_ID),
				saved.get(1).references());
	}

	@Test
	@DisplayName("the representation of the second one uses actual representations")
	void test4_1() {
		assertEquals(MessageFormat.format("{0} is a {1}", ALICE_REPR, HUMAN_REPR),
				saved.get(1).representation());
	}

	@Test
	@DisplayName("the third one is '{@containerId} contains {@elementId}' ")
	void test5() {
		assertEquals(List.of(CONTAINS_ID, MY_MODEL_ID, element.id),
				saved.get(2).references());
	}

	@Test
	@DisplayName("the representation of the third one uses actual representations")
	void test5_1() {
		assertEquals(
				MessageFormat.format("{0} contains {1}", MY_MODEL_REPR, ALICE_REPR),
				saved.get(2).representation());
	}

	@Test
	@DisplayName("the references are saved")
	void test6() {
		createBridiElement.apply(MY_MODEL_ID, HUMAN_ID, ALICE_REPR, IS_A_ID,
				ALICE_ID, GOD_ID);
		@SuppressWarnings("unchecked")
		ArgumentCaptor<List<Bridi>> argument = ArgumentCaptor.forClass(List.class);
		verify(createBridiElement.saveBridi, times(2)).apply(argument.capture());
		List<Bridi> saved = argument.getValue();
		assertEquals(3, saved.size());
		assertEquals(List.of(IS_A_ID, ALICE_ID, GOD_ID), saved.get(0).references());
	}

	@Test
	@DisplayName("if the type does not exist, an IllegalArgumentException is thrown")
	void test7() {
		assertThrows(IllegalArgumentException.class,
				() -> createBridiElement.apply(MY_MODEL_ID, NONEXISTENT_ID, ALICE_REPR,
						IS_A_ID, ALICE_ID, GOD_ID));

	}

	@Test
	@DisplayName("if the container does not exist, an IllegalArgumentException is thrown")
	void test8() {
		assertThrows(IllegalArgumentException.class,
				() -> createBridiElement.apply(NONEXISTENT_ID, HUMAN_ID, ALICE_REPR,
						IS_A_ID, ALICE_ID, GOD_ID));
	}

	@Test
	@DisplayName("if the there is a nonexisting reference, an IllegalArgumentException is thrown")
	void test9() {
		assertThrows(IllegalArgumentException.class,
				() -> createBridiElement.apply(MY_MODEL_ID, HUMAN_ID, ALICE_REPR,
						NONEXISTENT_ID, ALICE_ID, GOD_ID));
	}

}
