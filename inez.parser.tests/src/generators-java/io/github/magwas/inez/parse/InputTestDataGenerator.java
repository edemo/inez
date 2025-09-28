package io.github.magwas.inez.parse;

import java.util.function.Supplier;

import io.github.magwas.runtime.GeneratorUtil;

public class InputTestDataGenerator
		implements Supplier<StringBuilder>, TestDataGeneratorConstants {

	@Override
	public StringBuilder get() {
		StringBuilder builder = new StringBuilder();
		GeneratorUtil.testDataBoilerPlate(builder, "");
		GeneratorUtil.mapToCode(INPUTS, GeneratorUtil.stringConstant("INPUT"),
				builder);
		builder.append("}\n");

		return builder;
	}
}
