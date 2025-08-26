package io.github.magwas.inez.ui.mindmap.visuals;

import org.eclipse.gef.fx.nodes.Connection;

import javafx.scene.paint.Color;

public class MindMapConnectionVisual extends Connection {

	public MindMapConnectionVisual() {
		ArrowHead endDecoration = new ArrowHead();
		endDecoration.setFill(Color.BLACK);
		setEndDecoration(endDecoration);
	}
}