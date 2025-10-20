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
	Inez inez;

	@Autowired
	PopulateUIService populateUI;

	@Autowired
	FullScreenRelatedState fullScreenRelatedState;

	void apply(final Stage primaryStage) throws IOException {
		fullScreenRelatedState.primaryStage = primaryStage;

		inez.initialize();

		fullScreenRelatedState.mainScene = new Scene(createMainScene.apply(), MAIN_WINDOW_HEIGHT, MAIN_WINDOW_WIDTH);

		fullScreenRelatedState.fullscreenScene = new Scene(new StackPane(), MAIN_WINDOW_HEIGHT, MAIN_WINDOW_WIDTH);

		populateUI.apply();

		primaryStage.setTitle(STAGE_TITLE);
		primaryStage.setScene(fullScreenRelatedState.mainScene);
		primaryStage.show();
	}
}
