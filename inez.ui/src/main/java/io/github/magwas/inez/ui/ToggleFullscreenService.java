package io.github.magwas.inez.ui;

import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToggleFullscreenService implements UIConstants {

	@Autowired
	UIState uiState;

	public void apply(final TabPane tabPane) {
		if (uiState.isFullscreen) {
			exitFullscreen();
		} else {
			enterFullscreen(tabPane);
		}
	}

	private void enterFullscreen(final TabPane tabPane) {
		uiState.fullScreened = tabPane;
		uiState.fullScreenedParent = (StackPane) tabPane.getParent();
		StackPane fullscreenRoot = (StackPane) uiState.fullscreenScene.getRoot();
		fullscreenRoot.getChildren().setAll(tabPane);
		fullscreenRoot.setStyle(FULL_SCREEN_ROOT_STYLE);

		fullscreenRoot.setOnMouseClicked(event -> {
			if (UIUtil.isDoubleClick(event)) {
				exitFullscreen();
			}
		});

		uiState.primaryStage.setScene(uiState.fullscreenScene);
		uiState.isFullscreen = true;
	}

	private void exitFullscreen() {
		uiState.primaryStage.setScene(uiState.mainScene);
		uiState.fullScreenedParent.getChildren().add(uiState.fullScreened);
		uiState.isFullscreen = false;
	}
}
