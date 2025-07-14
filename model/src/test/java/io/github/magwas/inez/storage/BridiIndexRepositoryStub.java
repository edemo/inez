package io.github.magwas.inez.storage;

import static org.mockito.Mockito.mock;

class BridiIndexRepositoryStub implements BridiIndexTestData {
	public static BridiIndexRepository stub() {
		BridiIndexRepository mock = mock(BridiIndexRepository.class);
		return mock;
	}
}
