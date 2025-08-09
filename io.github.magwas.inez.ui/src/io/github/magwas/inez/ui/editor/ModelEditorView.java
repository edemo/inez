package io.github.magwas.inez.ui.editor;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.swt.widgets.Composite;

import io.github.magwas.inez.element.BridiElement;

public class ModelEditorView extends GraphicalEditorWithFlyoutPalette {

	public static String ID = "io.github.magwas.inez.ui.EditorView";
	private PaletteRoot palette;

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		GraphicalViewer viewer = this.getGraphicalViewer();
		BridiElement themodel = ((EditorInput) this.getEditorInput()).element;
		viewer.addDropTargetListener(
				new ModelEditorDropTargetListener(themodel, 500));
		viewer.setEditPartFactory(new EditorPartFactory(this));
		ArrayList<EditPart> parts = new ArrayList<EditPart>();
		ModelEditPart part = new ModelEditPart();
		part.activate();
		parts.add(part);
		viewer.setContents(parts);
		new EditPartCreateCommand();
	}

	@Override
	protected DefaultEditDomain getEditDomain() {
		return new InezEditDomain(this);
	}

	@Override
	public void setFocus() {
		super.setFocus();
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		if (palette == null) {
			palette = new DiagramEditorPalette(
					((EditorInput) this.getEditorInput()).element);
		}
		return palette;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

}
