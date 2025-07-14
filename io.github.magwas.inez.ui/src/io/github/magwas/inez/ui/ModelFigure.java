package io.github.magwas.inez.ui;

import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.geometry.PointList;

public class ModelFigure extends PolygonShape {
	ModelFigure() {
		super();
		PointList points = new PointList();
		points.addPoint(-10, -10);
		points.addPoint(10, -10);
		points.addPoint(10, -10);
		points.addPoint(-10, 10);

		this.setPoints(points);

	}
}
