package io.github.magwas.inez.ui;

import jakarta.inject.Inject;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;

import io.github.magwas.inez.ui.tree.ModelTreeContentProvider;

public class View extends ViewPart {
	public static final String ID = "io.github.magwas.inez.ui.view";

	@Inject IWorkbench workbench;
	
	private TreeViewer viewer;
	

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		
		viewer.setContentProvider(new ModelTreeContentProvider());
		viewer.setLabelProvider(new ModelTreeLabelProvider(workbench));
		viewer.setInput("parent");
		
	}


	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
}