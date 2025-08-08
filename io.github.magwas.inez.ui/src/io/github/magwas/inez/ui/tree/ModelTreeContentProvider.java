package io.github.magwas.inez.ui.tree;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import io.github.magwas.inez.element.BridiElement;
import io.github.magwas.inez.osgi.BridiElementService;
import io.github.magwas.inez.ui.Application;

public class ModelTreeContentProvider implements ITreeContentProvider {

	BridiElementService bridiElementService;

	private void instantiateService() {
		BundleContext bc = Application.bundleContext;
		System.out.println("app bundle context:" + bc);
		ServiceReference<BridiElementService> ref = bc
				.getServiceReference(BridiElementService.class);
		bridiElementService = bc.getService(ref);
	}

	@Override
	public Object[] getElements(Object inputElement) {
		instantiateService();
		System.out.println("getElements " + inputElement);
		return bridiElementService.root().getChildren().toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return ((BridiElement) parentElement).getChildren().toArray();
	}

	@Override
	public Object getParent(Object element) {
		return ((BridiElement) element).getParent();
	}

	@Override
	public boolean hasChildren(Object element) {
		return !((BridiElement) element).getChildren().toList().isEmpty();
	}

}
