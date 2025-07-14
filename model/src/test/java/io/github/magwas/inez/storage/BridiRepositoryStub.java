package io.github.magwas.inez.storage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import io.github.magwas.inez.BridiTestData;

class BridiRepositoryStub implements BridiTestData {
	public static BridiRepository stub() {
		BridiRepository mock = mock(BridiRepository.class);
		when(mock.findAllByRepresentation(THING_REPR)).thenReturn(List.of(THING));
		when(mock.findAllByRepresentation(GO_REPRESENTATION))
				.thenReturn(List.of(GO1, GO2));
		when(mock.findById(IS_A_REPR)).thenReturn(Optional.of(IS_A));
		when(mock.findById(SUMTI_IS_A_THING_REPR))
				.thenReturn(Optional.of(SUMTI_IS_A_THING));
		when(mock.findById(THING_REPR)).thenReturn(Optional.of(THING));
		when(mock.save(any())).thenAnswer(x -> x.getArguments()[0]);
		return mock;
	}
}
