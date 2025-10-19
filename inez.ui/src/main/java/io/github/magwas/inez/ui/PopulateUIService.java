package io.github.magwas.inez.ui;

import java.io.IOException;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopulateUIService {

	@Autowired
	UIState uiState;

	@Autowired
	CreateTreeViewTabService createTreeViewTab;

	void apply() throws IOException {
		Tab treeviewTab = createTreeViewTab.apply();
		((TabPane) uiState.mainArea.getItems().get(0)).getTabs().add(treeviewTab);
		CreateExampleContentUtil.createExampleContent(uiState);
	}
}
