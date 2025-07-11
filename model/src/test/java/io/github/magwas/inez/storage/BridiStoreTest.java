package io.github.magwas.inez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.TestBase;
import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.BridiTestData;

class BridiStoreTest extends TestBase implements BridiTestData {

	@InjectMocks
	BridiStore bridiStore;

	@Test
	@DisplayName("save saves the bridi and updates the history\n"
			+ "- if there was no previous bridi saved, the history contains null for the old bridi")
	void test() {
		bridiStore.save(BRIDI);
		verify(bridiStore.bridiRepository).save(BRIDI);
		StoreCommand last = bridiStore.history.getLast();
		assertEquals(BridiStoreOperation.SAVE, last.operation);
		assertEquals(BRIDI, last.now);
		assertEquals(null, last.old);
	}

	@Test
	@DisplayName("- if there was previous bridi saved, the history contains it for the old bridi")
	void test1() {
		bridiStore.save(THING_CHANGED);
		verify(bridiStore.bridiRepository).save(THING_CHANGED);
		StoreCommand last = bridiStore.history.getLast();
		assertEquals(BridiStoreOperation.SAVE, last.operation);
		assertEquals(THING_CHANGED, last.now);
		assertEquals(THING, last.old);
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
		verify(bridiStore.bridiRepository).delete(THING);
		StoreCommand last = bridiStore.history.getLast();
		assertEquals(BridiStoreOperation.DELETE, last.operation);
		assertEquals(null, last.now);
		assertEquals(THING, last.old);
	}

	@Test
	@DisplayName("findById simply delegates to the repository")
	void test5() {
		Optional<Bridi> actual = bridiStore.findById(THING_REPR);
		verify(bridiStore.bridiRepository).findById(THING_REPR);
		assertEquals(THING, actual.get());
	}

}
