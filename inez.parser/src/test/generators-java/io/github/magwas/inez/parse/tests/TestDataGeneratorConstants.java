package io.github.magwas.inez.parse.tests;

import io.github.magwas.konveyor.testing.TestUtil;

public interface TestDataGeneratorConstants {
	String REPRESENTATIONS = TestUtil.loadResourceAsString("representations");
	String IDS = TestUtil.loadResourceAsString("ids");
	String IDREFERENCES = TestUtil.loadResourceAsString("idreferences");
	String INPUTS = TestUtil.loadResourceAsString("inputs");
	String REFERENCES = TestUtil.loadResourceAsString("references");
	String OUTPUTS = TestUtil.loadResourceAsString("outputs");
	String PARSE_TEXT_STUB_DATA = TestUtil
			.loadResourceAsString("parse_text_stubdata");

}
