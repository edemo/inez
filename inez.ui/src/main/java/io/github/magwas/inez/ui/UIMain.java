package io.github.magwas.inez.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UIMain extends Application implements UIConstants {

	@Override
	public void start(final Stage primaryStage) throws IOException {
		ApplicationContext springContext = new AnnotationConfigApplicationContext(UIConfig.class);
		SetupPrimaryStageService setupPrimaryStage = springContext.getBean(SetupPrimaryStageService.class);

		setupPrimaryStage.apply(primaryStage);
	}

	public static void main(final String[] args) {
		launch(args);
	}
}
