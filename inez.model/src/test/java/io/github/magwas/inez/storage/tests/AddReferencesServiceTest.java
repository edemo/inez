package io.github.magwas.inez.storage.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;

import io.github.magwas.inez.parse.tests.IdTestData;
import io.github.magwas.inez.storage.AddReferencesService;
import io.github.magwas.inez.storage.model.BridiReference;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;
import io.github.magwas.konveyor.testing.TestBase;
import io.github.magwas.konveyor.testing.TestUtil;

class AddReferencesServiceTest extends TestBase implements IdTestData {

	@InjectMocks
	AddReferencesService addReferences;

	@Test
	@DisplayName("for null references does nothing")
	void test() throws IllegalAccessException {
		addReferences.apply(ALICE_ID, null);
		verifyNoInteractions(TestUtil.dependency(addReferences, BridiReferenceRepository.class));
	}

	@Test
	@DisplayName("for empty references does nothing")
	void test1() throws IllegalAccessException {
		addReferences.apply(ALICE_ID, List.of());
		verifyNoInteractions(TestUtil.dependency(addReferences, BridiReferenceRepository.class));
	}

	@Test
	@DisplayName("for each reference adds an entry to the bridireferencerepository")
	void test2() throws IllegalAccessException {
		addReferences.apply(ALICE_ID, List.of(IS_A_ID, SUMTI_ID));
		ArgumentCaptor<BridiReference> argument = ArgumentCaptor.forClass(BridiReference.class);
		verify(TestUtil.dependency(addReferences, BridiReferenceRepository.class), times(2))
				.save(argument.capture());
		List<BridiReference> values = argument.getAllValues();
		assertEquals(ALICE_ID, values.get(0).bridiId());
		assertEquals(ALICE_ID, values.get(1).bridiId());
		assertEquals(IS_A_ID, values.get(0).selbriId());
		assertEquals(IS_A_ID, values.get(1).selbriId());
		assertEquals(IS_A_ID, values.get(0).sumtiId());
		assertEquals(SUMTI_ID, values.get(1).sumtiId());
	}
}
