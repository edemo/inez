package io.github.magwas.inez.ui;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

public class ModelEditPart extends AbstractGraphicalEditPart {

	@Override
	protected IFigure createFigure() {
		return new ModelFigure();
	}

	@Override
	protected void createEditPolicies() {
		// installEditPolicy(REQ_RECONNECT_TARGET, null);
	}
}
