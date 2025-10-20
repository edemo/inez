module io.github.magwas.inez.ui {
	requires javafx.controls;
	requires javafx.fxml;
	requires inez.model;
	requires spring.context;
	requires spring.beans;
	requires spring.core;
	requires spring.data.keyvalue;
	requires spring.data.commons;
	requires konveyor.base;
	requires javafx.base;
	requires javafx.graphics;

	opens io.github.magwas.inez.ui to
			javafx.fxml,
			javafx.graphics,
			spring.beans,
			spring.core;
}
