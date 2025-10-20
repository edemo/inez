package io.github.magwas.inez.ui;

import javafx.scene.input.MouseEvent;

public class UIUtil {
	public static boolean isDoubleClick(final MouseEvent event) {
		return event.getClickCount() == 2;
	}
}
