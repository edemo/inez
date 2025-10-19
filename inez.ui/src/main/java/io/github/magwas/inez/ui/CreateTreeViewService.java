package io.github.magwas.inez.ui;

import java.io.IOException;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Inez;
import io.github.magwas.inez.element.BridiElement;

@Service
public class CreateTreeViewService {

	@Autowired
	Inez inez;

	TreeView<BridiElement> apply() throws IOException {

		BridiElement rootElement = inez.root();

		BridiElementTreeItem rootItem = new BridiElementTreeItem(rootElement);
		TreeView<BridiElement> treeView = new TreeView<>(rootItem);

		treeView.setEditable(true);
		Callback<TreeView<BridiElement>, TreeCell<BridiElement>> callback = new BridiCellFactory();
		treeView.setCellFactory(callback);

		treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				System.out.println("Selected: " + newValue.getValue());
			}
		});
		return treeView;
	}
}
