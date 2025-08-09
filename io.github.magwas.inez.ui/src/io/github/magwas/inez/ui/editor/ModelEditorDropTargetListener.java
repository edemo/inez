package io.github.magwas.inez.ui.editor;

import java.text.MessageFormat;
import java.util.List;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;

import io.github.magwas.inez.element.BridiElement;

public class ModelEditorDropTargetListener
		implements TransferDropTargetListener {

	BridiElement viewer;
	private Integer sizeX;

	public ModelEditorDropTargetListener(BridiElement viewer, Integer sizeX) {
		this.viewer = viewer;
		this.sizeX = sizeX;
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
		int x = event.x;
		int y = event.y;
		if (selection instanceof TreeSelection) {
			List<BridiElement> selected = ((TreeSelection) selection).toList();
			for (BridiElement sel : selected) {
				System.out.println(MessageFormat.format("{0} shows {1} at ({2},{3})",
						viewer, sel, x, y));
				x += 100;
				if (x > (sizeX - 100)) {
					y += 100;
					x -= (sizeX - 100);
				}
			}
		} else {
			throw new Error("unknown selection type");
		}
	}

	@Override
	public void dropAccept(DropTargetEvent event) {
		System.out.println("dropAccept");
	}

}
