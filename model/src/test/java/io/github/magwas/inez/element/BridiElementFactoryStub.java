package io.github.magwas.inez.element;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BridiElementFactoryStub {
	public static BridiElementFactory stub() {
		BridiElementFactory mock = mock(BridiElementFactory.class);
		when(mock.apply(any())).then(x -> new BridiElement(x.getArgument(0)));
		return mock;
	}
}
