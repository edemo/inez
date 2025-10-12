package io.github.magwas.inez.parse;

import java.text.MessageFormat;
import java.util.function.Supplier;

import io.github.magwas.runtime.GeneratorUtil;

public class ParseTextServiceStubGenerator
		implements Supplier<StringBuilder>, TestDataGeneratorConstants {

	@Override
	public StringBuilder get() {
		StringBuilder builder = GeneratorUtil.stubBoilerPlate("""
				import java.util.stream.Stream;
				""", "ParserOutputTestData");
		GeneratorUtil.linesOf(PARSE_TEXT_STUB_DATA).map(line -> {
			String[] splat = line.split(",");
			return MessageFormat.format("""
							when(mock.apply({0})).thenReturn(Stream.of({1}));
					""", splat[0], splat[1]);
		}).forEach(builder::append);
		return GeneratorUtil.stubTail(builder);
	}

}
