package io.github.magwas.inez.storage.tests;

import static org.mockito.Mockito.verify;

import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.storage.RemoveReferencesService;
import io.github.magwas.inez.storage.repository.BridiReferenceRepository;
import io.github.magwas.inez.tests.BridiTestData;
import io.github.magwas.konveyor.testing.TestBase;
import io.github.magwas.konveyor.testing.TestUtil;

class RemoveReferencesTest extends TestBase implements BridiTestData {

	@InjectMocks
	RemoveReferencesService removeReferences;

	@Test
	@DisplayName("removes references from the BridiReferenceRepository\n"
			+ "- removes all the sumti references for the bridi")
	void test() throws IllegalAccessException {
		removeReferences.apply(SUMTI_IS_A_THING_ID, SUMTI_IS_A_THING_REFERENCES);
		BridiReferenceRepository bridiReferenceRepository =
				TestUtil.dependency(removeReferences, BridiReferenceRepository.class);
		IntStream.range(0, SUMTI_IS_A_THING_REFERENCES.size() - 1).forEach(position -> verify(bridiReferenceRepository)
				.deleteBybridiIdAndPositionAndSumtiId(
						SUMTI_IS_A_THING_ID, position, SUMTI_IS_A_THING_REFERENCES.get(position)));
	}
}
