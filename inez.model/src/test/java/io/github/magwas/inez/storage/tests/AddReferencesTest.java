package io.github.magwas.inez.storage.tests;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.parse.tests.ReferenceTestData;
import io.github.magwas.inez.storage.AddReferencesService;
import io.github.magwas.inez.storage.model.tests.BridiReferenceTestData;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;
import io.github.magwas.konveyor.testing.TestBase;
import io.github.magwas.konveyor.testing.TestUtil;

class AddReferencesTest extends TestBase implements BridiReferenceTestData, ReferenceTestData {
	@InjectMocks
	AddReferencesService addReferences;

	@Test
	@DisplayName("adds all the references of the bridi to the repository")
	void test() throws IllegalAccessException {
		addReferences.apply(SUMTI_IS_A_THING_ID, SUMTI_IS_A_THING_REFERENCES);
		BridiReferenceRepository dependency = TestUtil.dependency(addReferences, BridiReferenceRepository.class);
		SUMTI_IS_A_THING_REFERENCELIST.forEach(x -> verify(dependency).save(x));
	}

	@Test
	@DisplayName("if references is null, does nothing")
	void test1() throws IllegalAccessException {
		addReferences.apply(SUMTI_IS_A_THING_ID, null);
		verifyNoInteractions(TestUtil.dependency(addReferences, BridiReferenceRepository.class));
	}
}
