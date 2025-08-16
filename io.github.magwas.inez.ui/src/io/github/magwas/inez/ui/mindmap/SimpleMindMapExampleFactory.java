package io.github.magwas.inez.ui.mindmap;

import org.eclipse.gef.geometry.planar.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

public class SimpleMindMapExampleFactory {

	private static final double WIDTH = 150;

	public SimpleMindMap createComplexExample() {
		SimpleMindMap mindMap = new SimpleMindMap();

		MindMapNode center = new MindMapNode();
		center.setTitle("The Core Idea");
		center.setDescription("This is my Core idea");
		Display display = Display.getCurrent();
		center.setColor(display.getSystemColor(SWT.COLOR_GREEN));
		center.setBounds(new Rectangle(250, 50, WIDTH, 100));

		mindMap.addChildElement(center);

		MindMapNode child = null;
		for (int i = 0; i < 5; i++) {
			child = new MindMapNode();
			child.setTitle("Association #" + i);
			child
					.setDescription("I just realized, this is related to the core idea!");
			child.setColor(display.getSystemColor(SWT.COLOR_BLUE));

			child.setBounds(new Rectangle(50 + (i * 200), 250, WIDTH, 100));
			mindMap.addChildElement(child);

			MindMapConnection conn = new MindMapConnection();
			conn.connect(center, child);
			mindMap.addChildElement(conn);
		}

		MindMapNode child2 = new MindMapNode();
		child2.setTitle("Association #4-2");
		child2.setDescription("I just realized, this is related to the last idea!");
		child2.setColor(display.getSystemColor(SWT.COLOR_GRAY));
		child2.setBounds(new Rectangle(250, 550, WIDTH, 100));
		mindMap.addChildElement(child2);

		MindMapConnection conn = new MindMapConnection();
		conn.connect(child, child2);
		mindMap.addChildElement(conn);

		return mindMap;
	}

	public SimpleMindMap createSingleNodeExample() {
		SimpleMindMap mindMap = new SimpleMindMap();

		MindMapNode center = new MindMapNode();
		center.setTitle("The Core Idea");
		center.setDescription(
				"This is my Core idea. I need a larger Explanation to it, so I can test the warpping.");
		center.setColor(Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW));
		center.setBounds(new Rectangle(20, 50, WIDTH, 100));

		mindMap.addChildElement(center);

		return mindMap;
	}
}