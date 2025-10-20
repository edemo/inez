package io.github.magwas.inez.ui;

import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToggleFullscreenService implements UIConstants {

	@Autowired
	FullScreenRelatedState fullScreenRelatedState;

	public void apply(final TabPane tabPane) {
		if (fullScreenRelatedState.isFullscreen) {
			exitFullscreen();
		} else {
			enterFullscreen(tabPane);
		}
	}

	private void enterFullscreen(final TabPane tabPane) {
		fullScreenRelatedState.fullScreened = tabPane;
		fullScreenRelatedState.fullScreenedParent = (StackPane) tabPane.getParent();
		StackPane fullscreenRoot = (StackPane) fullScreenRelatedState.fullscreenScene.getRoot();
		fullscreenRoot.getChildren().setAll(tabPane);
		fullscreenRoot.setStyle(FULL_SCREEN_ROOT_STYLE);

		fullscreenRoot.setOnMouseClicked(event -> {
			if (UIUtil.isDoubleClick(event)) {
				exitFullscreen();
			}
		});

		fullScreenRelatedState.primaryStage.setScene(fullScreenRelatedState.fullscreenScene);
		fullScreenRelatedState.isFullscreen = true;
	}

	private void exitFullscreen() {
		fullScreenRelatedState.primaryStage.setScene(fullScreenRelatedState.mainScene);
		fullScreenRelatedState.fullScreenedParent.getChildren().add(fullScreenRelatedState.fullScreened);
		fullScreenRelatedState.isFullscreen = false;
	}
}
