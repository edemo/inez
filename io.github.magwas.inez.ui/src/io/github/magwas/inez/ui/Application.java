package io.github.magwas.inez.ui;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Application implements IApplication, BundleActivator {

	public static BundleContext bundleContext;

	@Override
	public Object start(IApplicationContext context) {
		System.err.println("appStart");
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

	@Override
	public void start(BundleContext context) throws Exception {
		bundleContext = context;
		System.out.println("bundle start. context: " + bundleContext);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
	}

}
