package io.github.magwas.inez.ui.editor;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

public class ModelFigure extends PolygonShape {
	private Label label;
	private RectangleFigure rectangle;

	ModelFigure() {
		super();
		PointList points = new PointList();
		points.addPoint(-10, -10);
		points.addPoint(10, -10);
		points.addPoint(10, -10);
		points.addPoint(-10, 10);

		this.setPoints(points);

	}

	@Override
	public void paintFigure(Graphics graphics) {
		Rectangle r = getBounds().getCopy();
		setConstraint(rectangle, new Rectangle(0, 0, r.width, r.height));
		setConstraint(label, new Rectangle(0, 0, r.width, r.height));
	}

	public Label getLabel() {
		return label;
	}

}
