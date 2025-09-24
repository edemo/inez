package io.github.magwas.inez.storage;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.storage.model.BridiReferenceTestData;
import io.github.magwas.testing.TestBase;

class AddReferencesTest extends TestBase
		implements BridiReferenceTestData {
	@InjectMocks
	AddReferencesService addReferences;

	@Test
	@DisplayName("adds all the references of the bridi to the repository")
	void test() {
		addReferences.apply(SUMTI_IS_A_THING_ID, SUMTI_IS_A_THING_REFERENCES);
		SUMTI_IS_A_THING_REFERENCELIST
				.forEach(x -> verify(addReferences.bridiReferenceRepository).save(x));
	}

	@Test
	@DisplayName("if references is null, does nothing")
	void test1() {
		addReferences.apply(SUMTI_IS_A_THING_ID, null);
		verifyNoInteractions(addReferences.bridiReferenceRepository);
	}

}
