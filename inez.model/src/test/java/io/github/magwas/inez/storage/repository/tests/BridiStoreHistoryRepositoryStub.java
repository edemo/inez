package io.github.magwas.inez.storage.repository.tests;

import static org.mockito.Mockito.mock;

import io.github.magwas.inez.storage.repository.BridiStoreHistoryRepository;

public class BridiStoreHistoryRepositoryStub {
	static BridiStoreHistoryRepository stub() {
		return mock(BridiStoreHistoryRepository.class);
	}
}
