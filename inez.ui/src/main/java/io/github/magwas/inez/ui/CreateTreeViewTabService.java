package io.github.magwas.inez.ui;

import java.io.IOException;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.element.BridiElement;

@Service
public class CreateTreeViewTabService implements UIConstants {

	@Autowired
	CreateTreeViewService createTreeView;

	Tab apply() throws IOException {
		Tab treeviewTab = new Tab(TREEVIEW_LABEL_TEXT);
		TreeView<BridiElement> treeView = createTreeView.apply();

		treeView.setPrefWidth(TREEVIEW_PREFERRED_WIDTH);
		ScrollPane scrollPane = new ScrollPane(treeView);
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);

		treeviewTab.setContent(scrollPane);
		treeviewTab.setClosable(true);
		return treeviewTab;
	}
}
