package io.github.magwas.inez.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import io.github.magwas.inez.element.BridiElement;

public class BridiElementTreeItem extends TreeItem<BridiElement> {

	private boolean isFirstTimeChildren = true;
	private boolean isFirstTimeLeaf = true;
	private boolean isLeaf;

	public BridiElementTreeItem(BridiElement rootElement) {
		super(rootElement);
	}

	@Override
	public ObservableList getChildren() {
		if (isFirstTimeChildren) {
			isFirstTimeChildren = false;
			// First call: load children dynamically
			super.getChildren().setAll(buildChildren(this));
		}
		return super.getChildren();
	}

	@Override
	public boolean isLeaf() {
		if (isFirstTimeLeaf) {
			isFirstTimeLeaf = false;
			isLeaf = 0 == getValue().getChildren().count();
		}
		return isLeaf;
	}

	private ObservableList buildChildren(BridiElementTreeItem treeItem) {
		ObservableList<BridiElementTreeItem> children = FXCollections.observableArrayList();
		treeItem.getValue().getChildren().forEach(x -> children.add(new BridiElementTreeItem(x)));
		return children;
	}
}
