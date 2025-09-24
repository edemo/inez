package io.github.magwas.inez.osgi;

import static org.mockito.Mockito.mock;

public class SpringBootBundleActivatorStub {
	public static SpringBootBundleActivator stub() {
        return mock(SpringBootBundleActivator.class);
	}
}
