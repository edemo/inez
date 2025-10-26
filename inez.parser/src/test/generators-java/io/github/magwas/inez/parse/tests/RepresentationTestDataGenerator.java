package io.github.magwas.inez.parse.tests;

import java.text.MessageFormat;
import java.util.function.Supplier;

import io.github.magwas.konveyor.runtime.GeneratorUtil;

public class RepresentationTestDataGenerator
		implements Supplier<StringBuilder>, TestDataGeneratorConstants {

	@Override
	public StringBuilder get() {
		StringBuilder builder = GeneratorUtil.testDataBoilerPlate("");
		GeneratorUtil.mapToCode(REPRESENTATIONS,
				GeneratorUtil.stringConstant("REPR"), builder);
		GeneratorUtil.mapToCode(IDREFERENCES, line -> {
			String[] parts = line.split(",", 2);
			return MessageFormat.format("""
							String {0}_REFERENCE = "@{0}";
					""", parts[0].trim());
		}, builder);
		return GeneratorUtil.testDataTail(builder);
	}

}
