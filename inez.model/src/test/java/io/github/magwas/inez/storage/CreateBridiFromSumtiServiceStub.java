package io.github.magwas.inez.storage;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.github.magwas.inez.BridiTestData;
import io.github.magwas.inez.storage.model.SumtiTestData;

public class CreateBridiFromSumtiServiceStub implements BridiTestData, SumtiTestData {
	public static CreateBridiFromSumtiService stub() {
		CreateBridiFromSumtiService mock = mock(CreateBridiFromSumtiService.class);
		when(mock.apply(THING_SUMTI)).thenReturn(THING);
		when(mock.apply(SUMTI_IS_A_THING_CHANGED_SUMTI)).thenReturn(SUMTI_IS_A_THING_CHANGED);
		when(mock.apply(SUMTI_IS_A_THING_SUMTI)).thenReturn(SUMTI_IS_A_THING);
		when(mock.apply(GO_SUMTI)).thenReturn(GO);
		when(mock.apply(GO2_SUMTI)).thenReturn(GO2);
		when(mock.apply(null)).thenThrow(new NullPointerException());
		return mock;
	}
}
