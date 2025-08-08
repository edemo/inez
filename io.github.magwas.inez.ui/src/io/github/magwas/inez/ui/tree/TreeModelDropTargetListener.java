package io.github.magwas.inez.ui.tree;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;

public class TreeModelDropTargetListener implements DropTargetListener {

	public TreeModelDropTargetListener(TreeViewer viewer) {
	}

	@Override
	public void dragEnter(DropTargetEvent event) {
		System.out.println("dragEnter");
	}

	@Override
	public void dragLeave(DropTargetEvent event) {
		System.out.println("dragLeave");
	}

	@Override
	public void dragOperationChanged(DropTargetEvent event) {
		System.out.println("dragOperationChanged");
	}

	@Override
	public void dragOver(DropTargetEvent event) {
	}

	@Override
	public void drop(DropTargetEvent event) {
		System.out.println("drop at " + event.item.getData());
		System.out.println(
				"dropped:" + LocalSelectionTransfer.getTransfer().getSelection());
	}

	@Override
	public void dropAccept(DropTargetEvent event) {
		System.out.println("dropAccept " + event.detail);
	}

}
