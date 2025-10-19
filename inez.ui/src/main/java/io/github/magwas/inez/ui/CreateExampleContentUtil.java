package io.github.magwas.inez.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CreateExampleContentUtil implements UIConstants {

	public static void createExampleContent(final UIState uiState) {
		createTabPaneContent((TabPane) uiState.mainArea.getItems().get(1));
		createMenus(uiState.menuBar);
		createToolButtons(uiState.toolBar);
		createRightPanelContents((TabPane) uiState.mainArea.getItems().get(2));
		createStatusBarContents(uiState.statusBar);
	}

	public static ScrollPane createDashboardContent() {
		VBox content = new VBox(PADDING);
		content.setPadding(new Insets(PADDING));

		Label welcomeLabel = new Label("Welcome to the Application");
		welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

		TextArea textArea = new TextArea();
		textArea.setPromptText("Enter your notes here...");
		textArea.setPrefRowCount(10);

		Button actionButton = new Button("Perform Action");

		content.getChildren().addAll(welcomeLabel, textArea, actionButton);

		ScrollPane scrollPane = new ScrollPane(content);
		scrollPane.setFitToWidth(true);
		return scrollPane;
	}

	public static ScrollPane createDocumentsContent() {
		TableView<String> tableView = new TableView<>();
		return new ScrollPane(tableView);
	}

	public static VBox createSettingsContent() {
		VBox settings = new VBox(PADDING);
		settings.setPadding(new Insets(PADDING));

		settings.getChildren()
				.addAll(
						new Label("Application Settings"),
						new Separator(),
						new CheckBox("Enable auto-save"),
						new CheckBox("Show notifications"),
						new ComboBox<>());

		return settings;
	}

	public static void createTabPaneContent(TabPane tabPane) {
		Tab dashboardTab = new Tab("Dashboard");
		ScrollPane dashboardContent = createDashboardContent();
		dashboardTab.setContent(dashboardContent);
		dashboardTab.setClosable(false);
		Tab documentsTab = new Tab("Documents");
		documentsTab.setContent(createDocumentsContent());
		documentsTab.setClosable(false);

		Tab settingsTab = new Tab("Settings");
		settingsTab.setContent(createSettingsContent());
		settingsTab.setClosable(false);

		tabPane.getTabs().addAll(dashboardTab, documentsTab, settingsTab);
	}

	public static void createToolButtons(ToolBar toolBar) {
		toolBar.getItems()
				.addAll(
						new Button("New"),
						new Button("Open"),
						new Separator(),
						new Button("Save"),
						new Button("Print"));
	}

	public static void createMenus(MenuBar menuBar) {
		Menu fileMenu = new Menu("File");
		fileMenu.getItems()
				.addAll(new MenuItem("New"), new MenuItem("Open"), new SeparatorMenuItem(), new MenuItem("Exit"));

		Menu editMenu = new Menu("Edit");
		editMenu.getItems().addAll(new MenuItem("Cut"), new MenuItem("Copy"), new MenuItem("Paste"));

		menuBar.getMenus().addAll(fileMenu, editMenu);
	}

	public static void createRightPanelContents(final TabPane rightPanel) {
		Tab propertiesTab = new Tab("Properties");

		CheckBox checkBox1 = new CheckBox("Option 1");
		CheckBox checkBox2 = new CheckBox("Option 2");
		Slider slider = new Slider(0, 100, 50);

		VBox propertiesBox = new VBox(
				PADDING, new Label("checks and balances:"), checkBox1, checkBox2, new Label("Volume:"), slider);
		propertiesBox.setStyle(RIGHT_PANEL_STYLE);
		propertiesTab.setContent(propertiesBox);
		ScrollPane scrollPane = new ScrollPane(propertiesBox);
		scrollPane.setFitToWidth(true);

		propertiesTab.setContent(scrollPane);

		rightPanel.getTabs().add(propertiesTab);
	}

	public static void createStatusBarContents(final HBox statusBar) {
		Label statusLabel = new Label("Ready");
		ProgressBar progressBar = new ProgressBar(0);
		progressBar.setPrefWidth(PROGRESS_BAR_PREFERRED_WIDTH);

		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);

		statusBar.getChildren().addAll(statusLabel, spacer, progressBar);
	}
}
