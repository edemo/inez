package io.github.magwas.inez;

import java.text.MessageFormat;
import java.util.function.Supplier;

import io.github.magwas.runtime.GeneratorUtil;
import io.github.magwas.runtime.RuntimeConstants;
import io.github.magwas.testing.TestUtil;

public class BridiTestDataGenerator implements Supplier<StringBuilder> {
	String BRIDI_PATTERN = "\tBridi {0} = new Bridi({1}, {2},{3});\n";

	@Override
	public StringBuilder get() {
		StringBuilder builder = GeneratorUtil.testDataBoilerPlate("""
				import java.util.List;
				import io.github.magwas.inez.parse.IdTestData;
				import io.github.magwas.inez.parse.IdUtil;
				import io.github.magwas.inez.parse.ParserConstants;
				import io.github.magwas.inez.parse.ReferenceTestData;

				""", "IdTestData", "ReferenceTestData");
		String bridies = TestUtil.loadResourceAsString("bridies");
		GeneratorUtil.mapToCode(bridies, line -> MessageFormat.format(BRIDI_PATTERN,
				(Object[]) line.split(RuntimeConstants.COMMA, 4)), builder);
		return GeneratorUtil.testDataTail(builder);
	}

}
