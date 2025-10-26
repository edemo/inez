package io.github.magwas.inez.storage.repository.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.github.magwas.inez.storage.repository.ProblemRepository;
import io.github.magwas.inez.tests.InezProblemTestData;

public class ProblemRepositoryStub implements InezProblemTestData {
	static ProblemRepository stub() {
		ProblemRepository mock = mock(ProblemRepository.class);
		when(mock.findAll()).thenReturn(PROBLEMS);
		return mock;
	}
}
