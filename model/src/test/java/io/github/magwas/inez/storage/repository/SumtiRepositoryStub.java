package io.github.magwas.inez.storage.repository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import io.github.magwas.inez.storage.model.SumtiTestData;

public class SumtiRepositoryStub implements SumtiTestData {
	public static SumtiRepository stub() {
		SumtiRepository mock = mock(SumtiRepository.class);
		when(mock.findById(THING_ID)).thenReturn(Optional.of(THING_SUMTI));
		when(mock.findById(SUMTI_IS_A_THING_ID))
				.thenReturn(Optional.of(SUMTI_IS_A_THING_SUMTI));
		when(mock.findById(NOT_SAVED_ID)).thenReturn(Optional.empty());
		return mock;
	}

}
