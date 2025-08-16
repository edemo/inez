package io.github.magwas.inez.ui.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.fx.anchors.ChopBoxStrategy;
import org.eclipse.gef.fx.anchors.DynamicAnchor;
import org.eclipse.gef.fx.nodes.Connection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import io.github.magwas.inez.ui.mindmap.visuals.MindMapConnectionVisual;
import io.github.magwas.inez.ui.mindmap.visuals.MindMapNodeVisual;
import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ModelEditorView extends EditorPart {

	public static String ID = "io.github.magwas.inez.ui.editor";

	@Override
	public void createPartControl(Composite parent) {
		final FXCanvas fxCanvas = new FXCanvas(parent, SWT.NONE);
		Pane root = new Pane();

		// create state visuals
		MindMapNodeVisual node = new MindMapNodeVisual();
		node.setTitle("Test Node");
		node.setDescription("This is just a test node, to see, how it looks :)");
		node.relocate(50, 50);

		MindMapNodeVisual node2 = new MindMapNodeVisual();
		node2.setTitle("Test Node 2");
		node2.setDescription("This is just a test node, to see, how it looks :)");
		node2.relocate(150, 250);
		node2.setColor(Color.ALICEBLUE);

		Connection conn = new MindMapConnectionVisual();
		conn.setStartAnchor(new DynamicAnchor(node, new ChopBoxStrategy()));
		conn.setEndAnchor(new DynamicAnchor(node2, new ChopBoxStrategy()));

		root.getChildren().addAll(conn, node, node2);

		fxCanvas.setScene(new Scene(root, 1024, 768));
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
