package io.github.magwas.inez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.BridiStoreOperation;
import io.github.magwas.inez.BridiTestData;
import io.github.magwas.inez.storage.model.SumtiTestData;
import io.github.magwas.testing.TestBase;

class SaveBridiTest extends TestBase implements BridiTestData, SumtiTestData {

	@InjectMocks
	SaveBridiService saveBridi;

	@Test
	@DisplayName("saving a bridi returns the old bridi if exists, and")
	void test1() {
		assertEquals(SUMTI_IS_A_THING, saveBridi.apply(SUMTI_IS_A_THING_CHANGED));
	}

	@Test
	@DisplayName("- looks up the existing version of the sumti")
	void test3() {
		saveBridi.apply(SUMTI_IS_A_THING_CHANGED);
		verify(saveBridi.sumtiRepository).findById(SUMTI_IS_A_THING_ID);
	}

	@Test
	@DisplayName("- if there was an existing version,\n" + " - then creates a bridi from it")
	void test4() {
		saveBridi.apply(SUMTI_IS_A_THING_CHANGED);
		verify(saveBridi.createBridiFromSumti).apply(SUMTI_IS_A_THING_SUMTI);
	}

	@Test
	@DisplayName(" - and cleans its references")
	void test4_1() {
		saveBridi.apply(SUMTI_IS_A_THING_CHANGED);
		verify(saveBridi.removeReferences).apply(SUMTI_IS_A_THING_ID, SUMTI_IS_A_THING.references());
	}

	@Test
	@DisplayName("- stores the id and representation in the sumtiRepository")
	void test() {
		saveBridi.apply(SUMTI_IS_A_THING_CHANGED);
		verify(saveBridi.sumtiRepository).save(SUMTI_IS_A_THING_CHANGED_SUMTI);
	}

	@Test
	@DisplayName("- adds the references")
	void test_1() {
		saveBridi.apply(SUMTI_IS_A_THING_CHANGED);
		verify(saveBridi.addReferences).apply(SUMTI_IS_A_THING_ID, SUMTI_IS_A_THING_CHANGED.references());
	}

	@Test
	@DisplayName("- adds the save operation to the history with SAVE, the old and new bridi")
	void test2() {
		saveBridi.apply(SUMTI_IS_A_THING_CHANGED);
		verify(saveBridi.notifyStoreChange).apply(BridiStoreOperation.SAVE, SUMTI_IS_A_THING, SUMTI_IS_A_THING_CHANGED);
	}

	@Test
	@DisplayName("- if there was no previous sumti, uses null in the history for it")
	void test5() {
		saveBridi.apply(NONEXISTENT);
		verify(saveBridi.notifyStoreChange).apply(BridiStoreOperation.SAVE, null, NONEXISTENT);
	}

	@Test
	@DisplayName("the form with collection parameter saves all the bridis")
	void test6() {
		assertEquals(Set.of(SUMTI_IS_A_THING, THING), saveBridi.apply(Set.of(SUMTI_IS_A_THING_CHANGED, THING)));
	}
}
