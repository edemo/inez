package io.github.magwas.inez.ui.editor;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import io.github.magwas.inez.element.BridiElement;

public class ModelEditPart extends AbstractGraphicalEditPart
		implements EditPart {

	@Override
	protected IFigure createFigure() {
		return new ModelFigure();
	}

	@Override
	protected void createEditPolicies() {
	}

	@Override
	protected void refreshVisuals() {
		ModelFigure thefigure = (ModelFigure) getFigure();
		BridiElement themodel = (BridiElement) getModel();
		List<BridiElement> refs = themodel.getReferences().toList();
		Integer x = Integer.valueOf(refs.get(2).getRepresentation());
		Integer y = Integer.valueOf(refs.get(3).getRepresentation());
		AbstractGraphicalEditPart theparent = (AbstractGraphicalEditPart) getParent();

		thefigure.getLabel().setText(themodel.getRepresentation());
		Rectangle layout = new Rectangle(x, y, 50, 50);
		theparent.setLayoutConstraint(this, thefigure, layout);
	}
}
