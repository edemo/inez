package io.github.magwas.inez.storage.repository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.stream.Stream;

import io.github.magwas.inez.storage.model.BridiReferenceTestData;

public class BridiReferenceRepositoryStub implements BridiReferenceTestData {

	static void foo(String id, Integer pos, Stream<String> r) {
	}

	public static BridiReferenceRepository stub() {
		BridiReferenceRepository mock = mock(BridiReferenceRepository.class);
		when(mock.findAllBySumtiIdAndPosition(IS_A_ID, 0))
				.thenAnswer(params -> Set.of(TAUTOLOGY_REFERENCE_0,
						SUMTI_IS_A_THING_IS_A_THING_REFERENCE_0,
						THING_IS_A_SUMTI_REFERENCE_0, SUMTI_IS_A_THING_REFERENCE_0,
						TAUTOLOGY_IS_A_THING_REFERENCE_0));
		when(mock.findAllBySumtiIdAndPosition(THING_ID, 1)).thenAnswer(
				params -> Set.of(TAUTOLOGY_REFERENCE_1, THING_IS_A_SUMTI_REFERENCE_1));
		when(mock.findAllBySumtiIdAndPosition(THING_ID, 2))
				.thenAnswer(params -> Set.of(TAUTOLOGY_REFERENCE_2,
						SUMTI_IS_A_THING_IS_A_THING_REFERENCE_2,
						SUMTI_IS_A_THING_REFERENCE_2, STUFF_COULDBE_THING_REFERENCE_2,
						TAUTOLOGY_IS_A_THING_REFERENCE_2));

		when(mock.findAllByBridiId(SUMTI_IS_A_THING_ID))
				.thenAnswer(params -> Set.of(SUMTI_IS_A_THING_IS_A_THING_REFERENCE_0,
						SUMTI_IS_A_THING_IS_A_THING_REFERENCE_1,
						SUMTI_IS_A_THING_IS_A_THING_REFERENCE_2));

		return mock;
	}
}
