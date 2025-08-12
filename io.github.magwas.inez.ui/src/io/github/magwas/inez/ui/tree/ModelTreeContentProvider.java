package io.github.magwas.inez.ui.tree;

import org.eclipse.jface.viewers.ITreeContentProvider;

import io.github.magwas.inez.element.BridiElement;
import io.github.magwas.inez.ui.Application;

public class ModelTreeContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		System.out.println("getElements " + inputElement);
		return Application.inez.root().getChildren().toArray();
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
