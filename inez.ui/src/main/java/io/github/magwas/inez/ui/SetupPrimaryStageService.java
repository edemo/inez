package io.github.magwas.inez.ui;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.magwas.inez.Inez;

@Service
public class SetupPrimaryStageService implements UIConstants {

	@Autowired
	CreateMainSceneService createMainScene;

	@Autowired
	UIState uiState;

	@Autowired
	Inez inez;

	@Autowired
	PopulateUIService populateUI;

	void apply(final Stage primaryStage) throws IOException {
		uiState.primaryStage = primaryStage;

		inez.initialize();

		uiState.mainScene = new Scene(createMainScene.apply(), MAIN_WINDOW_SIZE_X, MAIN_WINDOW_SIZE_Y);

		uiState.fullscreenScene = new Scene(new StackPane(), MAIN_WINDOW_SIZE_X, MAIN_WINDOW_SIZE_Y);

		populateUI.apply();

		primaryStage.setTitle(STAGE_TITLE);
		primaryStage.setScene(uiState.mainScene);
		primaryStage.show();
	}
}
