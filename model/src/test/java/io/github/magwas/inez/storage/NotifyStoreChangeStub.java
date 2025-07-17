package io.github.magwas.inez.storage;

import static org.mockito.Mockito.mock;

public class NotifyStoreChangeStub {
	public static NotifyStoreChange stub() {
		NotifyStoreChange mock = mock(NotifyStoreChange.class);
		return mock;
	}
}
