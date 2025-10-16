package io.github.magwas.inez;

import java.text.MessageFormat;
import java.util.function.Supplier;

import io.github.magwas.runtime.GeneratorUtil;
import io.github.magwas.runtime.RuntimeConstants;
import io.github.magwas.testing.TestUtil;

public class BridiTestDataGenerator
		implements Supplier<StringBuilder>, GeneratorPatternConstants {

	@Override
	public StringBuilder get() {
		StringBuilder builder = GeneratorUtil.testDataBoilerPlate(
				BRIDI_TEST_DATA_HEADER, "IdTestData", "ReferenceTestData");
		String bridies = TestUtil.loadResourceAsString("bridies");
		GeneratorUtil.mapToCode(bridies,
				line -> MessageFormat.format(BRIDI_TEST_DATA_PATTERN,
						(Object[]) line.split(RuntimeConstants.COMMA, 4)),
				builder);
		return GeneratorUtil.testDataTail(builder);
	}

}
