package io.github.magwas.inez.ui.tree;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import io.github.magwas.inez.element.BridiElement;
import io.github.magwas.inez.element.ElementConstants;
import io.github.magwas.inez.ui.editor.ModelEditorView;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class NewDiagramAction extends Action {

	@Inject
	IWorkbench workbench;

	IStructuredSelection selection;

	String applicableType = "CONTAINER";

	NewDiagramAction() {
		super("New model");
	}

	void setSelection(IStructuredSelection selection) {
		this.selection = selection;
	}

	@Override
	public void run() {
		BridiElement selectedElement = (BridiElement) selection.getFirstElement();
		if (!selectedElement.isInstance(BridiElement.CONTAINER_ID))
			selectedElement = selectedElement.getParent();
		BridiElement model = selectedElement.create(selectedElement.id,
				ElementConstants.DIAGRAM_ID,
				"DiagramModel of " + selectedElement.getRepresentation());
		System.out.println("action on " + selectedElement);
		System.out.println("model:" + model);
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getActivePage();
		MyEditorInput input = new MyEditorInput(model);
		IEditorPart editor;
		try {
			editor = page.openEditor(input, ModelEditorView.ID);
		} catch (PartInitException e) {
			throw new Error(e);
		}

	}

}
