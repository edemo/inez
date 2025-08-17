package io.github.magwas.inez.ui.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.github.magwas.inez.element.BridiElement;
import io.github.magwas.inez.element.BridiElementTestData;

public class MyEditorInputTest implements BridiElementTestData {

	@Test
	@DisplayName("exists")
	void test() {
		BridiElement mock = Mockito.mock(BridiElement.class);
		MyEditorInput e = new MyEditorInput(mock);
		assertEquals(true, e.exists());
	}

}
