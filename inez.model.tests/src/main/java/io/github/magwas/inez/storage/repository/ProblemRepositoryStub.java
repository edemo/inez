package io.github.magwas.inez.storage.repository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.github.magwas.inez.InezProblemTestData;

public class ProblemRepositoryStub implements InezProblemTestData {
	static ProblemRepository stub() {
		ProblemRepository mock = mock(ProblemRepository.class);
		when(mock.findAll()).thenReturn(PROBLEMS);
		return mock;
	}
}
