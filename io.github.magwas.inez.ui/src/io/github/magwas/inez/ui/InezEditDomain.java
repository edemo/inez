package io.github.magwas.inez.ui;

import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.ui.IEditorPart;

public class InezEditDomain extends DefaultEditDomain {

	public InezEditDomain(IEditorPart editorPart) {
		super(editorPart);
	}

	@Override
	public void nativeDragFinished(DragSourceEvent event, EditPartViewer viewer) {
		System.out.println("nativeDragFinished");
		super.nativeDragFinished(event, viewer);
	}

	@Override
	public void nativeDragStarted(DragSourceEvent event, EditPartViewer viewer) {
		System.out.println("nativeDragStarted");
		super.nativeDragStarted(event, viewer);
	}
}
