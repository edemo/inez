package io.github.magwas.inez.ui.tree;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ITreeContentProvider;

public class ModelTreeContentProvider implements ITreeContentProvider {
	
	Map<String,List<String>> contents = Map.of(
			"parent",List.of("one","two","three"),
			"one",List.of("four","five"),
			"two",List.of("seven","eight"),
			"three",List.of("nine","ten"),
			"four",List.of("eleven","twelve")
			);
	@Override
	public Object[] getElements(Object inputElement) {
		System.out.println("getElements "+inputElement);
		return contents.get(inputElement).toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return contents.get(parentElement).toArray();
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if(contents.containsKey(element))
			return true;
		return false;
	}

}
