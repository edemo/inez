package io.github.magwas.inez.ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.swt.widgets.Composite;
import org.osgi.service.component.annotations.Reference;

import io.github.magwas.inez.element.BridiElement;
import io.github.magwas.inez.osgi.BridiElementService;

public class ModelEditorView extends GraphicalEditorWithFlyoutPalette {

	public static String ID = "io.github.magwas.inez.ui.EditorView";
	private PaletteRoot palette;

	@Reference
	BridiElementService bridiElementService;

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		GraphicalViewer viewer = this.getGraphicalViewer();
		viewer.addDropTargetListener(new ModelEditorDropTargetListener(this));
		viewer.setEditPartFactory(new EditorPartFactory(this));
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
			palette = new DiagramEditorPalette(getModel());
		}
		return palette;
	}

	private BridiElement getModel() {
		return bridiElementService.root();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

}
