package io.github.magwas.inez.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import io.github.magwas.inez.Inez;
import io.github.magwas.inez.InezImpl;
import io.github.magwas.inez.element.BridiElement;

public class UIMain extends Application {

	private StackPane fullScreenedParent;
	private Node fullScreened;
	private Stage primaryStage;
	private Scene mainScene;
	private Scene fullscreenScene;
	private boolean isFullscreen = false;

	@SuppressWarnings("unchecked")
	@Override
	public void start(final Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		TreeView<BridiElement> treeView = createTreeView();

		// Create scene and stage
		BorderPane borderPane = new BorderPane();
		borderPane.setPadding(new Insets(10));
		VBox leftSidebar = createLeftSidebar();
		VBox topContainer = createTopSection();
		VBox rightPanel = createRightPanel();
		HBox statusBar = createStatusBar();
		TabPane mainContent = createMainContent();

		// Create a central SplitPane for Left, Center, and Right
		SplitPane horizontalSplit = new SplitPane();
		horizontalSplit.getItems().addAll(leftSidebar, mainContent, rightPanel);
		// Set the initial position of the dividers (adjust percentages as needed)
		horizontalSplit.setDividerPositions(0.2, 0.8);

		// Create a vertical SplitPane for Top, central area, and Bottom
		SplitPane verticalSplit = new SplitPane();
		verticalSplit.setOrientation(Orientation.VERTICAL);
		verticalSplit.getItems().addAll(topContainer, horizontalSplit, statusBar);
		verticalSplit.setDividerPositions(0.1, 0.9);

		mainScene = new Scene(verticalSplit, 800, 600);

		// Create fullscreen scene (initially empty, will be populated on demand)
		fullscreenScene = new Scene(new StackPane(), 800, 600);

		primaryStage.setTitle("Inez in Not Even Zenta");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	private TreeView<BridiElement> createTreeView() throws IOException {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(UIConfig.class);
		InezImpl inez = (InezImpl) ctx.getBean(Inez.class);
		inez.initialize();
		BridiElement rootElement = inez.root();
		// Create root item
		BridiElementTreeItem rootItem = new BridiElementTreeItem(rootElement);
		TreeView<BridiElement> treeView = new TreeView<>(rootItem);

		// Make items editable (optional)
		treeView.setEditable(true);
		Callback<TreeView<BridiElement>, TreeCell<BridiElement>> callback = new BridiCellFactory();
		treeView.setCellFactory(callback);

		// Handle selection changes
		treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				System.out.println("Selected: " + newValue.getValue());
				System.out.println(
						"children: " + newValue.getValue().getChildren().toList());
			}
		});
		return treeView;
	}

	public static void main(final String[] args) {
		launch(args);
	}

	private VBox createTopSection() {
		// Create menu bar
		MenuBar menuBar = new MenuBar();
		Menu fileMenu = new Menu("File");
		fileMenu.getItems()
				.addAll(new MenuItem("New"), new MenuItem("Open"), new SeparatorMenuItem(), new MenuItem("Exit"));

		Menu editMenu = new Menu("Edit");
		editMenu.getItems().addAll(new MenuItem("Cut"), new MenuItem("Copy"), new MenuItem("Paste"));

		menuBar.getMenus().addAll(fileMenu, editMenu);

		// Create toolbar
		ToolBar toolBar = new ToolBar(
				new Button("New"), new Button("Open"), new Separator(), new Button("Save"), new Button("Print"));

		// Combine menu and toolbar in VBox
		VBox topContainer = new VBox(menuBar, toolBar);
		return topContainer;
	}

	private VBox createLeftSidebar() throws IOException {
		VBox sidebar = new VBox(10);
		sidebar.setPadding(new Insets(10));
		sidebar.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: #cccccc; -fx-border-width: 0 1 0 0;");

		Label sidebarLabel = new Label("Navigation");
		sidebarLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		TreeView<BridiElement> treeView = createTreeView();

		treeView.setPrefWidth(150);

		sidebar.getChildren().addAll(sidebarLabel, treeView);
		return sidebar;
	}

	private VBox createRightPanel() {
		VBox rightPanel = new VBox(10);
		rightPanel.setPadding(new Insets(10));
		rightPanel.setPrefWidth(200);
		rightPanel.setStyle("-fx-background-color: #f8f8f8; -fx-border-color: #cccccc; -fx-border-width: 0 0 0 1;");

		Label propertiesLabel = new Label("Properties");
		propertiesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		// Property controls
		CheckBox checkBox1 = new CheckBox("Option 1");
		CheckBox checkBox2 = new CheckBox("Option 2");
		Slider slider = new Slider(0, 100, 50);

		VBox propertiesBox = new VBox(8, checkBox1, checkBox2, new Label("Volume:"), slider);

		rightPanel.getChildren().addAll(propertiesLabel, propertiesBox);
		return rightPanel;
	}

	private HBox createStatusBar() {
		HBox statusBar = new HBox(10);
		statusBar.setPadding(new Insets(5, 10, 5, 10));
		statusBar.setStyle("-fx-background-color: #e0e0e0; -fx-border-color: #cccccc; -fx-border-width: 1 0 0 0;");

		Label statusLabel = new Label("Ready");
		ProgressBar progressBar = new ProgressBar(0);
		progressBar.setPrefWidth(100);

		// Add spacer to push elements to edges
		Region spacer = new Region();
		HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

		statusBar.getChildren().addAll(statusLabel, spacer, progressBar);
		return statusBar;
	}

	private TabPane createMainContent() {
		TabPane tabPane = new TabPane();

		Tab tab1 = new Tab("Dashboard");
		ScrollPane dashboardContent = createDashboardContent();
		tab1.setContent(dashboardContent);
		tab1.setClosable(false);
		tabPane.setOnMouseClicked(event -> {
			System.out.println("clicked");
			if (event.getClickCount() == 2) {
				System.out.println("doubleclicked");
				// Get the tab that was clicked
				Node target = (Node) event.getTarget();
				System.out.println("target: " + target);
				toggleFullscreen(tabPane);
			}
		});
		Tab tab2 = new Tab("Documents");
		tab2.setContent(createDocumentsContent());
		tab2.setClosable(false);

		Tab tab3 = new Tab("Settings");
		tab3.setContent(createSettingsContent());
		tab3.setClosable(false);

		tabPane.getTabs().addAll(tab1, tab2, tab3);
		return tabPane;
	}

	private void toggleFullscreen(TabPane tabPane) {
		if (!isFullscreen) {
			// Enter fullscreen - create a new scene with the dashboard content
			enterFullscreen(tabPane);
		} else {
			// Exit fullscreen - return to main scene
			exitFullscreen();
		}
	}

	private void enterFullscreen(TabPane tabPane) {
		// Create a copy of the dashboard content for fullscreen (or use the original)
		// Set up the fullscreen scene
		fullScreened = tabPane;
		fullScreenedParent = (StackPane) tabPane.getParent();
		StackPane fullscreenRoot = (StackPane) fullscreenScene.getRoot();
		fullscreenRoot.getChildren().setAll(tabPane);
		fullscreenRoot.setStyle("-fx-background-color: #2c3e50;");

		// Add double-click handler to exit fullscreen
		fullscreenRoot.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {
				exitFullscreen();
			}
		});

		// Switch to fullscreen scene
		primaryStage.setScene(fullscreenScene);

		// Optional: Make stage fullscreen
		// primaryStage.setMaximized(true);

		isFullscreen = true;
	}

	private void exitFullscreen() {
		// Switch back to main scene
		primaryStage.setScene(mainScene);
		fullScreenedParent.getChildren().add(fullScreened);

		// Optional: Restore window size if you made it fullscreen
		// primaryStage.setMaximized(false);

		isFullscreen = false;
	}

	private ScrollPane createDashboardContent() {
		VBox content = new VBox(10);
		content.setPadding(new Insets(10));

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

	private ScrollPane createDocumentsContent() {
		TableView<String> tableView = new TableView<>();
		// Simplified table for example
		return new ScrollPane(tableView);
	}

	private VBox createSettingsContent() {
		VBox settings = new VBox(10);
		settings.setPadding(new Insets(10));

		settings.getChildren()
				.addAll(
						new Label("Application Settings"),
						new Separator(),
						new CheckBox("Enable auto-save"),
						new CheckBox("Show notifications"),
						new ComboBox<String>());

		return settings;
	}
}
