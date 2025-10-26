package io.github.magwas.inez.storage.model.tests;

import java.text.MessageFormat;
import java.util.function.Supplier;

import io.github.magwas.inez.tests.GeneratorPatternConstants;
import io.github.magwas.konveyor.runtime.GeneratorUtil;
import io.github.magwas.konveyor.runtime.RuntimeConstants;
import io.github.magwas.konveyor.testing.TestUtil;

public class BridiReferenceTestDataGenerator
		implements Supplier<StringBuilder>, GeneratorPatternConstants {

	@Override
	public StringBuilder get() {
		StringBuilder builder = GeneratorUtil
				.testDataBoilerPlate(BRIDI_REFERENCE_HEADER, "IdTestData");
		String bridireferences = TestUtil.loadResourceAsString("bridireferences");
		GeneratorUtil.mapToCode(bridireferences,
				line -> MessageFormat.format(BRIDI_REFERENCE_PATTERN,
						(Object[]) line.split(RuntimeConstants.COMMA)),
				builder);
		return GeneratorUtil.testDataTail(builder);
	}

}
