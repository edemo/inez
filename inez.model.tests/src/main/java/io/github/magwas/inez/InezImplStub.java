package io.github.magwas.inez;

import static org.mockito.Mockito.mock;

import io.github.magwas.inez.element.BridiElementTestData;

public class InezImplStub implements BridiElementTestData, BridiTestData {
	public static InezImpl stub() {
		return mock(InezImpl.class);
	}
}
