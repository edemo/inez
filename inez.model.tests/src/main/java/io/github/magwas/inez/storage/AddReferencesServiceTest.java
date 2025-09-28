package io.github.magwas.inez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;

import io.github.magwas.inez.parse.BridiFieldTestData;
import io.github.magwas.inez.storage.model.BridiReference;
import io.github.magwas.testing.TestBase;

class AddReferencesServiceTest extends TestBase implements BridiFieldTestData {

	@InjectMocks
	AddReferencesService addReferences;

	@Test
	@DisplayName("for null references does nothing")
	void test() {
		addReferences.apply(ALICE_ID, null);
		verifyNoInteractions(addReferences.bridiReferenceRepository);
	}

	@Test
	@DisplayName("for empty references does nothing")
	void test1() {
		addReferences.apply(ALICE_ID, List.of());
		verifyNoInteractions(addReferences.bridiReferenceRepository);
	}

	@Test
	@DisplayName("for each reference adds an entry to the bridireferencerepository")
	void test2() {
		addReferences.apply(ALICE_ID, List.of(IS_A_ID, SUMTI_ID));
		ArgumentCaptor<BridiReference> argument = ArgumentCaptor.forClass(BridiReference.class);
		verify(addReferences.bridiReferenceRepository, times(2)).save(argument.capture());
		List<BridiReference> values = argument.getAllValues();
		assertEquals(ALICE_ID, values.get(0).bridiId());
		assertEquals(ALICE_ID, values.get(1).bridiId());
		assertEquals(IS_A_ID, values.get(0).selbriId());
		assertEquals(IS_A_ID, values.get(1).selbriId());
		assertEquals(IS_A_ID, values.get(0).sumtiId());
		assertEquals(SUMTI_ID, values.get(1).sumtiId());
	}
}
