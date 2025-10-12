package io.github.magwas.inez.storage.model;

import java.text.MessageFormat;
import java.util.function.Supplier;

import io.github.magwas.runtime.GeneratorUtil;
import io.github.magwas.runtime.RuntimeConstants;
import io.github.magwas.testing.TestUtil;

public class BridiReferenceTestDataGenerator
		implements Supplier<StringBuilder> {
	String BRIDI_REFERENCE_PATTERN = """
			BridiReference {0}_REFERENCE_0 = new BridiReference(
					IdUtil.createID({0}_ID+0), {0}_ID, {1}_ID, 0, {1}_ID);
			BridiReference {0}_REFERENCE_1 = new BridiReference(
					IdUtil.createID({0}_ID+1), {0}_ID, {1}_ID, 1, {2}_ID);
			BridiReference {0}_REFERENCE_2 = new BridiReference(
					IdUtil.createID({0}_ID+2), {0}_ID, {1}_ID, 2, {3}_ID);
			List<BridiReference> {0}_REFERENCELIST = List.of(
					{0}_REFERENCE_0, {0}_REFERENCE_1,
					{0}_REFERENCE_2);
			""";

	@Override
	public StringBuilder get() {
		StringBuilder builder = GeneratorUtil.testDataBoilerPlate("""
				import io.github.magwas.inez.parse.IdTestData;
				import io.github.magwas.inez.parse.IdUtil;
				import java.util.List;
				""", "IdTestData");
		String bridireferences = TestUtil.loadResourceAsString("bridireferences");
		GeneratorUtil.mapToCode(bridireferences,
				line -> MessageFormat.format(BRIDI_REFERENCE_PATTERN,
						(Object[]) line.split(RuntimeConstants.COMMA)),
				builder);
		return GeneratorUtil.testDataTail(builder);
	}

}
