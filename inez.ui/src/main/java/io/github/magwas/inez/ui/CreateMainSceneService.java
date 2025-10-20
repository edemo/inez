package io.github.magwas.inez.ui;

import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateMainSceneService implements UIConstants {

	@Autowired
	UIState uiState;

	@Autowired
	ToggleFullscreenService toggleFullscreen;

	VBox apply() throws IOException {

		uiState.menuBar = new MenuBar();
		uiState.toolBar = new ToolBar();
		uiState.mainArea = createMainArea();
		uiState.statusBar = createStatusBar();

		VBox topContainer = new VBox(uiState.menuBar, uiState.toolBar);
		VBox content = new VBox();
		content.getChildren().addAll(topContainer, uiState.mainArea, uiState.statusBar);
		VBox.setVgrow(uiState.mainArea, Priority.ALWAYS);
		return content;
	}

	private SplitPane createMainArea() {
		SplitPane horizontalSplit = new SplitPane();
		horizontalSplit.setOrientation(Orientation.HORIZONTAL);
		horizontalSplit.getItems().addAll(createTabPane(), createTabPane(), createTabPane());
		horizontalSplit.setDividerPositions(LEFT_PANE_RATIO, RIGHT_PANE_RATIO);
		return horizontalSplit;
	}

	private HBox createStatusBar() {
		HBox statusBar = new HBox(PADDING);
		statusBar.setPadding(new Insets(SMALL_PADDING, PADDING, SMALL_PADDING, PADDING));
		statusBar.setStyle(STATUSBAR_STYLE);
		return statusBar;
	}

	private TabPane createTabPane() {
		TabPane tabPane = new TabPane();
		tabPane.setOnMouseClicked(event -> {
			if (UIUtil.isDoubleClick(event)) {
				toggleFullscreen.apply(tabPane);
			}
		});
		return tabPane;
	}
}
