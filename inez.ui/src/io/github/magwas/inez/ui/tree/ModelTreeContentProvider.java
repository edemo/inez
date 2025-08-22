package io.github.magwas.inez.ui.tree;

import org.eclipse.jface.viewers.ITreeContentProvider;

import io.github.magwas.inez.Inez;
import io.github.magwas.inez.element.BridiElement;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class ModelTreeContentProvider implements ITreeContentProvider {

	@Inject
	Inez inez;

	@Override
	public Object[] getElements(Object inputElement) {
		System.out.println("getElements " + inputElement);
		return inez.root().getChildren().toArray();
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
