package io.github.magwas.inez.ui;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

import io.github.magwas.inez.element.BridiElement;

public class BridiCellFactory implements Callback<TreeView<BridiElement>, TreeCell<BridiElement>> {

	@Override
	public TreeCell<BridiElement> call(TreeView<BridiElement> param) {
		return new TreeCell<>() {
			@Override
			protected void updateItem(final BridiElement item, final boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
					setGraphic(null);
				} else {
					setText(item.getRepresentation());
					TreeItem<BridiElement> treeItem = getTreeItem();
					if (treeItem != null) {
						setGraphic(treeItem.getGraphic());
					}
				}
			}
		};
	}
}
