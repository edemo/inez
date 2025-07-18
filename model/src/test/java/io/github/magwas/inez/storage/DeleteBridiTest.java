package io.github.magwas.inez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.BridiStoreOperation;
import io.github.magwas.inez.BridiTestData;
import io.github.magwas.inez.storage.model.SumtiTestData;
import io.github.magwas.testing.TestBase;

public class DeleteBridiTest extends TestBase
		implements BridiTestData, SumtiTestData {

	@InjectMocks
	DeleteBridiService deleteBridi;

	@Test
	@DisplayName("delete deletes the bridi and returns it\n")
	void test4() {
		assertEquals(SUMTI_IS_A_THING, deleteBridi.apply(SUMTI_IS_A_THING));
	}

	@Test
	@DisplayName("- if there was no such bridi saved, a NoSuchElementException is thrown")
	void test3() {
		assertThrows(NoSuchElementException.class,
				() -> deleteBridi.apply(NONEXISTENT));
	}

	@Test
	@DisplayName("- the references are cleared")
	void test() {
		deleteBridi.apply(SUMTI_IS_A_THING);
		verify(deleteBridi.removeReferences).apply(SUMTI_IS_A_THING_ID,
				SUMTI_IS_A_THING.references());
	}

	@Test
	@DisplayName("- the id and representation are cleared from the repository")
	void test1() {
		deleteBridi.apply(SUMTI_IS_A_THING);
		verify(deleteBridi.sumtiRepository).delete(SUMTI_IS_A_THING_SUMTI);
	}

	@Test
	@DisplayName("- notifies about the store change")
	void test2() {
		deleteBridi.apply(SUMTI_IS_A_THING);
		verify(deleteBridi.notifyStoreChange).apply(BridiStoreOperation.DELETE,
				SUMTI_IS_A_THING, null);
	}

}
