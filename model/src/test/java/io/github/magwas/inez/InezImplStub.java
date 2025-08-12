package io.github.magwas.inez;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import io.github.magwas.inez.element.BridiElementTestData;

public class InezImplStub implements BridiElementTestData, BridiTestData {
	public static InezImpl stub() {
		InezImpl mock = mock(InezImpl.class);
		when(mock.findById(HUMAN_ID)).thenReturn(Optional.of(HUMAN));
		when(mock.findById(MY_MODEL_ID)).thenReturn(Optional.of(MY_MODEL));
		return mock;
	}

}
