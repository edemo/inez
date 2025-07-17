package io.github.magwas.inez.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.BridiTestData;
import io.github.magwas.inez.Inez;
import io.github.magwas.inez.TestConfig;
import io.github.magwas.inez.impl.LogUtil;

@Tag("end-to-end")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
public class BridiStoreEndToEndTest implements BridiTestData {

	@Test
	void test() {
		Inez inez = Inez.getInstance();
		TEST_TEXT.forEach(sentence -> inez.create(sentence));
		inez.save(Set.of(GO1, GO2));
		inez.findAllByRepresentation(GO_REPRESENTATION)
				.forEach(x -> LogUtil.debug("go", x));
		String CECILE_EATS_BANANA_REPR = "{cecile} {{eats} {banana}}";
		String CECILE_LOOKS_AT_BANANA_REPR = "{cecile} {{looks at} {banana}}";
		Bridi cecile_eats_banana = assertGotTheBridi(CECILE_EATS_BANANA_REPR,
				inez.query(CECILE_EATS_BANANA_REPR));
		Bridi ceclie_looks_at_banana = assertGotTheBridi(
				CECILE_LOOKS_AT_BANANA_REPR, inez.query(CECILE_LOOKS_AT_BANANA_REPR));
		assertEquals(Set.of(cecile_eats_banana, ceclie_looks_at_banana),
				inez.query("{cecile} {{$?} {banana}}"));

		Bridi looks_at_banana = inez
				.findById(ceclie_looks_at_banana.references().get(2)).get();
		Bridi looks_at = inez.findById(looks_at_banana.references().get(1)).get();
		assertEquals("looks at", looks_at.representation());

	}

	private Bridi assertGotTheBridi(String expected, Set<Bridi> actual) {
		assertEquals(1, actual.size());
		Bridi bridi1 = actual.iterator().next();
		assertEquals(expected, bridi1.representation());
		return bridi1;
	}

}
