package io.github.magwas.inez.ui.editor;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;

public class ModelEditorDropTargetListener
		implements TransferDropTargetListener {

	ModelEditorView modelEditorView;

	public ModelEditorDropTargetListener(ModelEditorView modelEditorView) {
		this.modelEditorView = modelEditorView;
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
	}

	@Override
	public void dragLeave(DropTargetEvent event) {
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
		ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
		if (selection instanceof TreeSelection) {
			TreeSelection treeSelection = (TreeSelection) selection;
			treeSelection.forEach(x -> System.out.println(x + "," + x.getClass()));
			System.out.println(selection.getClass());
			System.out.println("dropped " + selection);
			System.out.println(event.currentDataType);
			System.out.println("at:" + event.x + "," + event.y);
			modelEditorView.getEditDomain().nativeDragFinished(null, null);
		} else {
			throw new Error("unknown selection type");
		}
	}

	@Override
	public void dropAccept(DropTargetEvent event) {
		System.out.println("dropAccept");
	}

}
