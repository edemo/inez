package io.github.magwas.inez.ui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.springframework.stereotype.Component;

@Component
public class UIState {

	Scene mainScene;
	Scene fullscreenScene;
	StackPane fullScreenedParent;
	Node fullScreened;
	Stage primaryStage;
	Boolean isFullscreen = false;

	public MenuBar menuBar;
	public ToolBar toolBar;
	public HBox statusBar;
	public SplitPane mainArea;
}
