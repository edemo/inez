package io.github.magwas.inez.ui;

import org.eclipse.gef.dnd.TransferDropTargetListener;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;

public class ModelEditorDropTargetListener
		implements TransferDropTargetListener {

	private ModelEditorView view;

	public ModelEditorDropTargetListener(ModelEditorView modelEditorView) {
		this.view = modelEditorView;
	}

	@Override
	public Transfer getTransfer() {
		System.out.println("Transfer");
		return LocalSelectionTransfer.getTransfer();
	}

	@Override
	public boolean isEnabled(DropTargetEvent event) {
		System.out.println("isEnabled");
		return true;
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
		System.out.println("dragOver");
	}

	@Override
	public void drop(DropTargetEvent event) {
		System.out
				.println("drop " + LocalSelectionTransfer.getTransfer().getSelection());
	}

	@Override
	public void dropAccept(DropTargetEvent event) {
		System.out.println("dropAccept");
	}

}
