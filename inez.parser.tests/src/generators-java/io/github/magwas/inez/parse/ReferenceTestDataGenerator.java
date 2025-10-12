package io.github.magwas.inez.parse;

import java.text.MessageFormat;
import java.util.function.Supplier;

import io.github.magwas.runtime.GeneratorUtil;

public class ReferenceTestDataGenerator
		implements Supplier<StringBuilder>, TestDataGeneratorConstants {

	@Override
	public StringBuilder get() {
		StringBuilder builder = GeneratorUtil.testDataBoilerPlate("""
				import java.util.List;
				""", "IdTestData", "InputTestData");
		GeneratorUtil.mapToCode(REFERENCES, line -> {
			String[] parts = line.split(",", 2);
			return MessageFormat.format("""
							List<String> {0}_REFERENCES = List.of({1});
					""", parts[0].trim(), parts[1]);
		}, builder);
		return GeneratorUtil.testDataTail(builder);
	}
}
