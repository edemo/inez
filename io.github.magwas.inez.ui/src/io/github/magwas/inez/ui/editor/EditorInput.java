package io.github.magwas.inez.ui.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import io.github.magwas.inez.element.BridiElement;

public class EditorInput implements IEditorInput {

	public BridiElement element;

	public EditorInput(BridiElement element) {
		this.element = element;
	}

	@Override
	public <T> T getAdapter(Class<T> adapter) {
		return null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "name";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return "Editor Tooltip";
	}

}
