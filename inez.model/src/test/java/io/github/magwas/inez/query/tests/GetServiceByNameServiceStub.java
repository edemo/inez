package io.github.magwas.inez.query.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.beans.BeansException;

import io.github.magwas.inez.element.ElementConstants;
import io.github.magwas.inez.query.BridiFunction;
import io.github.magwas.inez.query.GetServiceByNameService;

public class GetServiceByNameServiceStub implements ElementConstants {
	public static GetServiceByNameService stub() throws BeansException, ClassNotFoundException {
		GetServiceByNameService mock = mock(GetServiceByNameService.class);
		BridiFunction save = mock(BridiFunction.class);
		when(mock.apply(SAVE_FUNCTION_REF_ID)).thenReturn(save);
		return mock;
	}
}
