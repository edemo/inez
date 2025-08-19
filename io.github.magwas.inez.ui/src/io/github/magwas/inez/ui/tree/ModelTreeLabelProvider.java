package io.github.magwas.inez.ui.tree;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;

import io.github.magwas.inez.element.BridiElement;
import jakarta.inject.Inject;

public class ModelTreeLabelProvider implements ILabelProvider {

	@Inject
	@Optional
	IWorkbench workbench;

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getImage(Object element) {
		return workbench.getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
	}

	@Override
	public String getText(Object element) {
		return ((BridiElement) element).getRepresentation();
	}

}
