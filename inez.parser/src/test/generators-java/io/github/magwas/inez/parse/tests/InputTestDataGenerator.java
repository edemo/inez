package io.github.magwas.inez.parse.tests;

import java.util.function.Supplier;

import io.github.magwas.konveyor.runtime.GeneratorUtil;

public class InputTestDataGenerator
		implements Supplier<StringBuilder>, TestDataGeneratorConstants {

	@Override
	public StringBuilder get() {
		StringBuilder builder = GeneratorUtil.testDataBoilerPlate("");
		GeneratorUtil.mapToCode(INPUTS, GeneratorUtil.stringConstant("INPUT"),
				builder);
		return GeneratorUtil.testDataTail(builder);
	}
}
