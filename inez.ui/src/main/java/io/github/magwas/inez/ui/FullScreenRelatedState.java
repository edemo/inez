package io.github.magwas.inez.ui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.springframework.stereotype.Component;

@Component
class FullScreenRelatedState {
	Scene mainScene;
	Scene fullscreenScene;
	StackPane fullScreenedParent;
	Node fullScreened;
	Stage primaryStage;
	Boolean isFullscreen = false;
}
