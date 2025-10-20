package io.github.magwas.inez.ui;

import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;

import org.springframework.stereotype.Component;

@Component
public class UIState {

	public MenuBar menuBar;
	public ToolBar toolBar;
	public HBox statusBar;
	public SplitPane mainArea;
}
