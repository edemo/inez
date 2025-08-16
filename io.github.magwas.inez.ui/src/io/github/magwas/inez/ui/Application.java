package io.github.magwas.inez.ui;

import org.eclipse.e4.core.di.IInjector;
import org.eclipse.e4.core.di.InjectorFactory;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import io.github.magwas.inez.ui.tree.ModelTreeContentProvider;
import io.github.magwas.inez.ui.tree.ModelTreeLabelProvider;
import io.github.magwas.inez.ui.tree.NewDiagramAction;

public class Application implements IApplication {

	@Override
	public Object start(IApplicationContext context) {
		System.err.println("appStart");
		IInjector injector = InjectorFactory.getDefault();
		injector.addBinding(ModelTreeContentProvider.class);
		injector.addBinding(ModelTreeLabelProvider.class);
		injector.addBinding(NewDiagramAction.class);
		Display display = PlatformUI.createDisplay();
		try {
			int returnCode = PlatformUI.createAndRunWorkbench(display,
					new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			}
			return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
	}

	@Override
	public void stop() {
		System.err.println("appStop");
	}
}
