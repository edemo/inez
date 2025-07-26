package io.github.magwas.inez.ui;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.swt.widgets.Composite;

import io.github.magwas.inez.ui.tree.ModelTreeContentProvider;

public class ModelEditorView extends GraphicalEditorWithFlyoutPalette {

	public static String ID = "io.github.magwas.inez.ui.EditorView";
	private PaletteRoot palette;

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

	private Map<String, List<String>> getModel() {
		return ModelTreeContentProvider.contents;
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

}
