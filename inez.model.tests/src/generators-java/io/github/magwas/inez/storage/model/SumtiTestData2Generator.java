package io.github.magwas.inez.storage.model;

import java.util.function.Supplier;

import io.github.magwas.inez.parse.TestDataGeneratorConstants;
import io.github.magwas.runtime.GeneratorUtil;

public class SumtiTestData2Generator
		implements Supplier<StringBuilder>, TestDataGeneratorConstants {

	@Override
	public StringBuilder get() {
		StringBuilder builder = GeneratorUtil.testDataBoilerPlate("");
		GeneratorUtil.mapToCode(REPRESENTATIONS,
				GeneratorUtil.stringConstant("REPR"), builder);
		return GeneratorUtil.testDataTail(builder);
	}
}
