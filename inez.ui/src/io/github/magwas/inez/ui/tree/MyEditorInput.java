package io.github.magwas.inez.ui.tree;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import io.github.magwas.inez.element.BridiElement;

public class MyEditorInput implements IEditorInput {

	private BridiElement model;

	MyEditorInput(BridiElement model) {
		this.model = model;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return model.getRepresentation();
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return model.getRepresentation();
	}

}
