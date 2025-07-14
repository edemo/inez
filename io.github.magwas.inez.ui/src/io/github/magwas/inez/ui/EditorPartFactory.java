package io.github.magwas.inez.ui;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

public class EditorPartFactory implements EditPartFactory {

	public EditorPartFactory(ModelEditorView modelEditorView) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = new ModelEditPart();
		// TODO Auto-generated method stub
		return null;
	}

}
