package io.github.magwas.inez.storage;

import static org.mockito.Mockito.mock;

public class BridiStoreHistoryServiceStub {
	public static BridiStoreHistoryRepository stub() {
		BridiStoreHistoryRepository mock = mock(BridiStoreHistoryRepository.class);
		return mock;
	}

}
