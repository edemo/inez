package io.github.magwas.inez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.TestBase;
import io.github.magwas.inez.model.Bridi;
import io.github.magwas.inez.model.BridiTestData;

class BridiStoreTest extends TestBase implements BridiTestData {

	@InjectMocks
	BridiStore bridiStore;

	@Test
	@DisplayName("save saves the bridi and updates the history\n"
			+ "- if there was no previous bridi saved, the history contains null for the old bridi")
	void test() {
		bridiStore.save(List.of(BRIDI));
		fail();
	}

	@Test
	@DisplayName("- if there was previous bridi saved, the history contains it for the old bridi")
	void test1() {
		bridiStore.save(List.of(THING_CHANGED));
		fail();
	}

	@Test
	@DisplayName("delete deletes the bridi and updates the history\n"
			+ "if there was no previous bridi, a NoSuchElementException is thrown")
	void test3() {
		assertThrows(NoSuchElementException.class, () -> bridiStore.delete(BRIDI));
	}

	@Test
	@DisplayName("- if there was previous bridi saved, the history contains it for the old bridi")
	void test4() {
		bridiStore.delete(THING);
		fail();
	}

	@Test
	@DisplayName("findById simply delegates to the repository")
	void test5() {
		Optional<Bridi> actual = bridiStore.findById(THING_REPR);
		fail();
		assertEquals(THING, actual.get());
	}

	@Test
	@DisplayName("undo saves the old if it exists")
	void test6() {
		bridiStore.save(List.of(THING, THING_CHANGED));
		bridiStore.undo();
		fail();
	}

	@Test
	@DisplayName("undo deletes the new if old is null")
	void test7() {
		bridiStore.save(List.of(BRIDI));
		bridiStore.undo();
		fail();
	}

}
