package io.github.magwas.inez.storage;

import static org.mockito.Mockito.verify;

import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import io.github.magwas.inez.BridiTestData;
import io.github.magwas.testing.TestBase;

public class RemoveReferencesTest extends TestBase implements BridiTestData {

	@InjectMocks
	RemoveReferencesService removeReferences;

	@Test
	@DisplayName("removes references from the BridiReferenceRepository\n"
			+ "- removes all the sumti references for the bridi")
	void test() {
		removeReferences.apply(SUMTI_IS_A_THING_ID, SUMTI_IS_A_THING_REFERENCES);
		IntStream.range(0, SUMTI_IS_A_THING_REFERENCES.size() - 1)
				.forEach(position -> verify(removeReferences.bridiReferenceRepository)
						.deleteBybridiIdAndPositionAndSumtiId(SUMTI_IS_A_THING_ID, position,
								SUMTI_IS_A_THING_REFERENCES.get(position)));
	}

}
