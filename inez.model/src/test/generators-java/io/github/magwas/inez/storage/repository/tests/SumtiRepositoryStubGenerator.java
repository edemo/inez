package io.github.magwas.inez.storage.repository.tests;

import java.text.MessageFormat;
import java.util.function.Supplier;

import io.github.magwas.inez.tests.GeneratorPatternConstants;
import io.github.magwas.konveyor.runtime.GeneratorUtil;
import io.github.magwas.konveyor.runtime.RuntimeConstants;
import io.github.magwas.konveyor.testing.TestUtil;

public class SumtiRepositoryStubGenerator
		implements Supplier<StringBuilder>, GeneratorPatternConstants {

	@Override
	public StringBuilder get() {
		StringBuilder builder = GeneratorUtil
				.stubBoilerPlate(SUMTI_REPOSITORY_STUB_HEADER, "SumtiTestData");
		String sumties = TestUtil.loadResourceAsString("sumties");
		GeneratorUtil.mapToCode(sumties,
				line -> MessageFormat.format(SUMTI_REPOSITORY_STUB_PATTERN,
						(Object[]) line.split(RuntimeConstants.COMMA)),
				builder);
		builder.append(SUMTI_REPOSITORY_STUB_EXTRA);
		return GeneratorUtil.stubTail(builder);
	}

}
