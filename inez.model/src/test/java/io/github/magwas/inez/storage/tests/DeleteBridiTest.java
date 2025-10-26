package io.github.magwas.inez.storage.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.BridiStoreOperation;
import io.github.magwas.inez.storage.DeleteBridiService;
import io.github.magwas.inez.storage.NotifyStoreChangeService;
import io.github.magwas.inez.storage.RemoveReferencesService;
import io.github.magwas.inez.storage.model.tests.SumtiTestData;
import io.github.magwas.inez.storage.repository.SumtiRepository;
import io.github.magwas.inez.tests.BridiTestData;
import io.github.magwas.konveyor.testing.TestBase;
import io.github.magwas.konveyor.testing.TestUtil;

class DeleteBridiTest extends TestBase implements BridiTestData, SumtiTestData {

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
		assertThrows(NoSuchElementException.class, () -> deleteBridi.apply(NONEXISTENT));
	}

	@Test
	@DisplayName("- the references are cleared")
	void test() throws IllegalAccessException {
		deleteBridi.apply(SUMTI_IS_A_THING);
		verify(TestUtil.dependency(deleteBridi, RemoveReferencesService.class))
				.apply(SUMTI_IS_A_THING_ID, SUMTI_IS_A_THING.references());
	}

	@Test
	@DisplayName("- the id and representation are cleared from the repository")
	void test1() throws IllegalAccessException {
		deleteBridi.apply(SUMTI_IS_A_THING);
		verify(TestUtil.dependency(deleteBridi, SumtiRepository.class)).delete(SUMTI_IS_A_THING_SUMTI);
	}

	@Test
	@DisplayName("- notifies about the store change")
	void test2() throws IllegalAccessException {
		deleteBridi.apply(SUMTI_IS_A_THING);
		verify(TestUtil.dependency(deleteBridi, NotifyStoreChangeService.class))
				.apply(BridiStoreOperation.DELETE, SUMTI_IS_A_THING, null);
	}
}
