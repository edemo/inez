package io.github.magwas.inez.parse;

import java.text.MessageFormat;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import io.github.magwas.runtime.GeneratorUtil;

public class BridiFieldTestDataGenerator
		implements Supplier<StringBuilder>, BridiFieldTestDataGeneratorConstants {

	@Override
	public StringBuilder get() {
		List<String> definedIdlist = GeneratorUtil.linesOf(IDS)
				.map(s -> s.split(",")[0]).toList();
		Stream<String> generatedIds = GeneratorUtil.linesOf(REPRESENTATIONS)
				.map(s -> s.split(",")[0]).filter(x -> !definedIdlist.contains(x));
		StringBuilder builder = new StringBuilder();
		GeneratorUtil.testDataBoilerPlate(builder, """
				import java.util.List;
				""");
		GeneratorUtil.mapToCode(REPRESENTATIONS,
				GeneratorUtil.stringConstant("REPR"), builder);
		builder.append("\n");
		GeneratorUtil.mapToCode(generatedIds, line -> {
			String[] parts = line.split(",", 2);
			return MessageFormat.format("""
							String {0}_ID = IdUtil.createID({0}_REPR);
					""", parts[0].trim());
		}, builder);
		GeneratorUtil.mapToCode(IDS, GeneratorUtil.stringConstant("ID"), builder);
		builder.append("\n");
		GeneratorUtil.mapToCode(INPUTS, GeneratorUtil.stringConstant("INPUT"),
				builder);
		builder.append("\n");
		GeneratorUtil.mapToCode(IDREFERENCES, line -> {
			String[] parts = line.split(",", 2);
			return MessageFormat.format("""
							String {0}_REFERENCE = "@{0}";
					""", parts[0].trim());
		}, builder);
		builder.append("\n");
		GeneratorUtil.mapToCode(REFERENCES, line -> {
			String[] parts = line.split(",", 2);
			return MessageFormat.format("""
							List<String> {0}_REFERENCES = List.of({1});
					""", parts[0].trim(), parts[1]);
		}, builder);
		builder.append("\n");
		builder.append(EXTRA);
		builder.append("}\n");

		return builder;
	}
}
