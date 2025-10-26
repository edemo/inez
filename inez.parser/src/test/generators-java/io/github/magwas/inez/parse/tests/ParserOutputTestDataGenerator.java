package io.github.magwas.inez.parse.tests;

import java.text.MessageFormat;
import java.util.function.Supplier;

import io.github.magwas.konveyor.runtime.GeneratorUtil;

public class ParserOutputTestDataGenerator
		implements Supplier<StringBuilder>, TestDataGeneratorConstants {

	@Override
	public StringBuilder get() {
		StringBuilder builder = GeneratorUtil.testDataBoilerPlate("""
import java.util.Map;
import io.github.magwas.inez.parse.ParserOutput;
import io.github.magwas.inez.parse.ParserConstants;
""", "InputTestData", "ReferenceTestData");
		GeneratorUtil.linesOf(OUTPUTS).map(line -> {
			String[] parts = line.split(",", 3);
			String name = parts[0];
			String top = parts[1];
			String map = "";
			if (parts.length == 3)
				map = parts[1] + "," + parts[2];

			return MessageFormat.format(
					"\tParserOutput OUTPUT_{0} = new ParserOutput({1},Map.of({2}));\n",
					name, top, map);
		}).forEach(builder::append);
		return GeneratorUtil.testDataTail(builder);
	}

}
