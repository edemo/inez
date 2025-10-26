package io.github.magwas.inez.storage.tests;

import java.text.MessageFormat;
import java.util.function.Supplier;

import io.github.magwas.inez.tests.GeneratorPatternConstants;
import io.github.magwas.konveyor.runtime.GeneratorUtil;
import io.github.magwas.konveyor.runtime.RuntimeConstants;
import io.github.magwas.konveyor.testing.TestUtil;

public class FindAllByRepresentationServiceStubGenerator
		implements Supplier<StringBuilder>, GeneratorPatternConstants {

	@Override
	public StringBuilder get() {
		StringBuilder builder = GeneratorUtil.stubBoilerPlate(
				FIND_ALL_BY_REPRESENTATION_STUB_HEADER, "BridiTestData");
		String sumties = TestUtil.loadResourceAsString("byrepresentation");
		GeneratorUtil.mapToCode(sumties, line -> {
			return MessageFormat.format(FIND_ALL_BY_REPRESENTATION_STUB_PATTERN,
					(Object[]) line.split(RuntimeConstants.COMMA, 2));
		}, builder);
		return GeneratorUtil.stubTail(builder);
	}

}
