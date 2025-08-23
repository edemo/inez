package io.github.magwas.inez.ui.mindmap;

import java.util.ArrayList;
import java.util.List;

public class SimpleMindMap extends AbstractMindMapItem {

	private static final long serialVersionUID = 4667064215236604843L;

	public static final String PROP_CHILD_ELEMENTS = "childElements";

	private List<AbstractMindMapItem> childElements = new ArrayList<>();

	public void addChildElement(AbstractMindMapItem node) {
		childElements.add(node);
		pcs.firePropertyChange(PROP_CHILD_ELEMENTS, null, node);
	}

	public void addChildElement(AbstractMindMapItem node, int idx) {
		childElements.add(idx, node);
		pcs.firePropertyChange(PROP_CHILD_ELEMENTS, null, node);
	}

	public List<AbstractMindMapItem> getChildElements() {
		return childElements;
	}

	public void removeChildElement(AbstractMindMapItem node) {
		childElements.remove(node);
		pcs.firePropertyChange(PROP_CHILD_ELEMENTS, node, null);
	}
}
