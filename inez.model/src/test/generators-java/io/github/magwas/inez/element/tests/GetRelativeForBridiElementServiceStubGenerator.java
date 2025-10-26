package io.github.magwas.inez.element.tests;

import java.text.MessageFormat;
import java.util.function.Supplier;

import io.github.magwas.inez.tests.GeneratorPatternConstants;
import io.github.magwas.konveyor.runtime.GeneratorUtil;
import io.github.magwas.konveyor.runtime.RuntimeConstants;
import io.github.magwas.konveyor.testing.TestUtil;

public class GetRelativeForBridiElementServiceStubGenerator
		implements Supplier<StringBuilder>, GeneratorPatternConstants {

	@Override
	public StringBuilder get() {
		StringBuilder builder = GeneratorUtil.stubBoilerPlate("""
import io.github.magwas.inez.element.GetRelativeForBridiElementService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;
""", "BridiElementTestData");
		String relatives = TestUtil.loadResourceAsString("relatives");
		GeneratorUtil.mapToCode(relatives,
				line -> MessageFormat.format(GET_RELATIVE_FOR_BRIDI_ELEMENT_PATTERN,
						(Object[]) line.split(RuntimeConstants.COMMA)),
				builder);
		return GeneratorUtil.stubTail(builder);
	}

}
