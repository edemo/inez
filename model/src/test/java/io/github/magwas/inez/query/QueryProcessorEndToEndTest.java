package io.github.magwas.inez.query;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.magwas.TestBase;
import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.BridiTestData;
import io.github.magwas.inez.Inez;
import io.github.magwas.inez.TestConfig;
import io.github.magwas.inez.TestUtil;

@Tag("end-to-end")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class QueryProcessorEndToEndTest extends TestBase implements BridiTestData {

	private Inez factory;

	@Test
	void test1() {
		factory = Inez.getInstance();
		save(TEST_TEXT);

		assertQuery(Set.of("alice"), "alice");
		assertQuery(Set.of("{alice} {{eats} {banana}}"),
				"{alice} {{eats} {banana}}");
		assertQuery(Set.of("{alice} {{eats} {banana}}", "{bob} {{eats} {banana}}",
				"{cecile} {{eats} {banana}}"), "{$?} {{eats} {banana}}");
		assertQuery(
				Set.of("{cecile} {{eats} {banana}}", "{cecile} {{looks at} {banana}}"),
				"{cecile} {{$?} {banana}}");
		assertQuery(Set.of("{alice} {{eats} {banana}}", "{alice} {{eats} {chips}}"),
				"{alice} {{eats} {$?}}");
	}

	private void save(List<String> sentences) {
		for (String sentence : sentences) {
			factory.create(sentence);
		}
	}

	private Set<Bridi> assertQuery(Set<String> expected, String query) {
		Set<Bridi> result = factory.query(query);
		Set<String> actual = result.stream().map(bridi -> bridi.id())
				.collect(Collectors.toSet());
		if (!expected.equals(actual)) {
			System.out.println("actual:");
			actual.forEach((x) -> System.out.println(x));
			System.out.println("expected:");
			expected.forEach((x) -> System.out.println(x));
			TestUtil.diffCollections(expected, actual);
		}
		assertEquals(expected, actual);
		return result;
	}

}
