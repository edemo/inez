package io.github.magwas.inez.element.tests;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Constructor;

import io.github.magwas.inez.element.BridiElement;
import io.github.magwas.inez.element.BridiElementFactory;

public class BridiElementFactoryStub {
	public static BridiElementFactory stub() throws NoSuchMethodException {
		BridiElementFactory mock = mock(BridiElementFactory.class);
		Constructor<BridiElement> declaredConstructor = BridiElement.class.getDeclaredConstructor(String.class);
		declaredConstructor.setAccessible(true);
		when(mock.apply(any())).thenAnswer(invocation -> {
			String argument = invocation.getArgument(0, String.class);
			return declaredConstructor.newInstance(argument);
		});
		return mock;
	}
}
