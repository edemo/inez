package io.github.magwas.inez.storage.repository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Set;

import io.github.magwas.inez.storage.model.BridiReferenceTestData;

public class BridiReferenceRepositoryStub implements BridiReferenceTestData {

	public static BridiReferenceRepository stub() {
		BridiReferenceRepository mock = mock(BridiReferenceRepository.class);

		when(mock.findAllByBridiId(SUMTI_IS_A_THING_ID))
				.thenAnswer(params -> Set.of(
						SUMTI_IS_A_THING_REFERENCE_0, SUMTI_IS_A_THING_REFERENCE_1, SUMTI_IS_A_THING_REFERENCE_2));
		when(mock.findAllBySelbriIdAndSumtiIdAndPosition(IS_A_ID, THING_ID, 2))
				.thenAnswer(params -> Set.of(
						TAUTOLOGY_REFERENCE_2,
						SUMTI_IS_A_THING_IS_A_THING_REFERENCE_2,
						SUMTI_IS_A_THING_REFERENCE_2,
						TAUTOLOGY_IS_A_THING_REFERENCE_2));

		return mock;
	}
}
