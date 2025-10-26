package io.github.magwas.inez.tests;

import static org.mockito.Mockito.mock;

import io.github.magwas.inez.InezImpl;

public class InezImplStub {
	public static InezImpl stub() {
		return mock(InezImpl.class);
	}
}
