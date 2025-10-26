package io.github.magwas.inez.parse.tests;

import java.text.MessageFormat;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import io.github.magwas.konveyor.runtime.GeneratorUtil;

public class IdTestDataGenerator
		implements Supplier<StringBuilder>, TestDataGeneratorConstants {

	@Override
	public StringBuilder get() {
		List<String> definedIdlist = GeneratorUtil.linesOf(IDS)
				.map(s -> s.split(",")[0]).toList();
		Stream<String> generatedIds = GeneratorUtil.linesOf(REPRESENTATIONS)
				.map(s -> s.split(",")[0]).filter(x -> !definedIdlist.contains(x));
		StringBuilder builder = GeneratorUtil.testDataBoilerPlate("""
                        import io.github.magwas.inez.parse.IdUtil;
                        """,
				"RepresentationTestData");
		GeneratorUtil.mapToCode(generatedIds, line -> {
			String[] parts = line.split(",", 2);
			return MessageFormat.format("""
							String {0}_ID = IdUtil.createID({0}_REPR);
					""", parts[0].trim());
		}, builder);
		GeneratorUtil.mapToCode(IDS, GeneratorUtil.stringConstant("ID"), builder);
		return GeneratorUtil.testDataTail(builder);
	}
}
