package io.github.magwas.inez.storage.repository;

import static org.mockito.Mockito.mock;

import io.github.magwas.inez.storage.repository.BridiStoreHistoryRepository;

public class BridiStoreHistoryServiceStub {
	public static BridiStoreHistoryRepository stub() {
		BridiStoreHistoryRepository mock = mock(BridiStoreHistoryRepository.class);
		return mock;
	}

}
