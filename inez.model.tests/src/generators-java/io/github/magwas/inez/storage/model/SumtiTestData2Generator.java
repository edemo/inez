package io.github.magwas.inez.storage.model;

import java.util.function.Supplier;

import io.github.magwas.inez.parse.BridiFieldTestDataGeneratorConstants;
import io.github.magwas.runtime.GeneratorUtil;

public class SumtiTestData2Generator
		implements Supplier<StringBuilder>, BridiFieldTestDataGeneratorConstants {

	@Override
	public StringBuilder get() {
		StringBuilder builder = new StringBuilder();
		GeneratorUtil.testDataBoilerPlate(builder, "");
		GeneratorUtil.mapToCode(REPRESENTATIONS,
				GeneratorUtil.stringConstant("REPR"), builder);
		builder.append("}\n");
		return builder;
	}
}
