package io.github.magwas.inez.ui.tree;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;

import io.github.magwas.inez.Inez;
import jakarta.inject.Inject;

public class TreeModelView extends ViewPart {
	public static final String ID = "io.github.magwas.inez.ui.treeModelView";

	@Inject
	IWorkbench workbench;
	@Inject
	Inez inez;
	@Inject
	ModelTreeContentProvider modelTreeContentProvider;
	@Inject
	ModelTreeLabelProvider modelTreeLabelProvider;
	@Inject
	NewDiagramAction newDiagramAction;

	private TreeViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(modelTreeContentProvider);
		viewer.setLabelProvider(modelTreeLabelProvider);
		viewer.setInput("parent");
		Transfer[] transfertypes = new Transfer[] {
				LocalSelectionTransfer.getTransfer() };
		int allDrops = DND.DROP_DEFAULT | DND.DROP_COPY | DND.DROP_LINK
				| DND.DROP_MOVE | DND.DROP_TARGET_MOVE;
		viewer.addDragSupport(allDrops, transfertypes,
				new TreeModelDragSourceLIstener(viewer));
		viewer.addDropSupport(allDrops, transfertypes,
				new TreeModelDropTargetListener(viewer));

		MenuManager menuManager = new MenuManager();
		menuManager.setRemoveAllWhenShown(true);
		menuManager.addMenuListener(manager -> {
			IStructuredSelection selection = viewer.getStructuredSelection();
			newDiagramAction.setSelection(selection);
			menuManager.add(newDiagramAction);

		});

		Control control = viewer.getControl();
		Menu menu = menuManager.createContextMenu(control);
		control.setMenu(menu);
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

}