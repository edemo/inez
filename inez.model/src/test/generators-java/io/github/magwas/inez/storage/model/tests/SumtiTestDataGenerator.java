package io.github.magwas.inez.storage.model.tests;

import java.text.MessageFormat;
import java.util.function.Supplier;

import io.github.magwas.inez.tests.GeneratorPatternConstants;
import io.github.magwas.konveyor.runtime.GeneratorUtil;
import io.github.magwas.konveyor.runtime.RuntimeConstants;
import io.github.magwas.konveyor.testing.TestUtil;

public class SumtiTestDataGenerator
		implements Supplier<StringBuilder>, GeneratorPatternConstants {

	@Override
	public StringBuilder get() {
		StringBuilder builder = GeneratorUtil.testDataBoilerPlate(
				"""
import io.github.magwas.inez.parse.tests.IdTestData;
import io.github.magwas.inez.storage.model.Sumti;
""", "IdTestData");
		String sumties = TestUtil.loadResourceAsString("sumties");
		GeneratorUtil.mapToCode(sumties,
				line -> MessageFormat.format(SUMTI_TESTDATA_PATTERN,
						(Object[]) line.split(RuntimeConstants.COMMA)),
				builder);
		return GeneratorUtil.testDataTail(builder);
	}

}
