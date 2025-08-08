package io.github.magwas.inez.ui.editor;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

public class EditorPartFactory implements EditPartFactory {

	public EditorPartFactory(ModelEditorView modelEditorView) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ModelEditPart createEditPart(EditPart context, Object model) {
		return new ModelEditPart();
	}

}
