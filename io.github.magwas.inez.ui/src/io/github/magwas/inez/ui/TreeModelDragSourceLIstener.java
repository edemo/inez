package io.github.magwas.inez.ui;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;

public class TreeModelDragSourceLIstener implements DragSourceListener {

	private TreeViewer viewer;

	public TreeModelDragSourceLIstener(TreeViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void dragStart(DragSourceEvent event) {
		System.out.println("dragStart");
		IStructuredSelection selection = (IStructuredSelection) viewer
				.getSelection();
		LocalSelectionTransfer.getTransfer().setSelection(selection);
		event.doit = true;

	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		System.out.println("dragSetData");
		event.data = LocalSelectionTransfer.getTransfer().getSelection();
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		System.out.println("dragFinished " + event);
		LocalSelectionTransfer.getTransfer().setSelection(null);
	}

}
