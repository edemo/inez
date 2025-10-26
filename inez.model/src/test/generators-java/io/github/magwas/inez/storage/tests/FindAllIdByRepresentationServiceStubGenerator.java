package io.github.magwas.inez.storage.tests;

import java.text.MessageFormat;
import java.util.function.Supplier;

import io.github.magwas.inez.tests.GeneratorPatternConstants;
import io.github.magwas.konveyor.runtime.GeneratorUtil;
import io.github.magwas.konveyor.runtime.RuntimeConstants;
import io.github.magwas.konveyor.testing.TestUtil;

public class FindAllIdByRepresentationServiceStubGenerator
		implements Supplier<StringBuilder>, GeneratorPatternConstants {

	@Override
	public StringBuilder get() {
		StringBuilder builder = GeneratorUtil.stubBoilerPlate(
				FIND_ALL_BY_REPRESENTATION_STUB_HEADER, "BridiTestData");
		String sumties = TestUtil.loadResourceAsString("byrepresentation");
		GeneratorUtil.mapToCode(sumties, line -> {
			String[] parts = line.split(RuntimeConstants.COMMA, 2);
			String[] rest = parts[1].split(RuntimeConstants.COMMA);
			return MessageFormat.format(FIND_ALL_ID_BY_REPRESENTATION_STUB_PATTERN,
					parts[0], parts[1].replaceAll(",", "_ID,").replaceAll("$", "_ID"));
		}, builder);
		return GeneratorUtil.stubTail(builder);
	}

}
