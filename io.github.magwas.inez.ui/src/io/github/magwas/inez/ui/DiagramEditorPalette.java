package io.github.magwas.inez.ui;

import java.util.List;
import java.util.Map;

import org.eclipse.gef.palette.PaletteRoot;

public class DiagramEditorPalette extends PaletteRoot{

	private Map<String, List<String>> model;

	public DiagramEditorPalette(Map<String, List<String>> model) {
		this.model = model;
	}

	public static Object getViewpoint(Map<String, List<String>> model2) {
		// TODO Auto-generated method stub
		return null;
	}


}
