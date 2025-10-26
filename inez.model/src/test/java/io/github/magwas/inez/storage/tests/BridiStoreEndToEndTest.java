package io.github.magwas.inez.storage.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.InezImpl;
import io.github.magwas.inez.query.tests.QueryProcessorTestData;
import io.github.magwas.inez.tests.BridiTestData;
import io.github.magwas.inez.tests.TestConfig;
import io.github.magwas.konveyor.runtime.LogUtil;
import io.github.magwas.konveyor.testing.TestUtil;

@Tag("end-to-end")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class BridiStoreEndToEndTest implements QueryProcessorTestData, BridiTestData {

	@Autowired
	InezImpl inez;

	@Test
	void test() {
		inez.create(TEST_TEXT).toArray();
		assertEquals(List.of(ALICE), inez.findAllByRepresentation("alice").toList());

		inez.save(Set.of(GO, GO2));
		inez.findAllByRepresentation(GO_REPR).forEach(x -> LogUtil.debug("go", x));
		String CECILE_EATS_BANANA_REPR = "{cecile} {{eats} {banana}}";
		String CECILE_LOOKS_AT_BANANA_REPR = "{cecile} {{looks at} {banana}}";
		Bridi cecile_eats_banana = assertGotTheBridi(CECILE_EATS_BANANA_REPR, inez.query(CECILE_EATS_BANANA_REPR));
		Bridi ceclie_looks_at_banana =
				assertGotTheBridi(CECILE_LOOKS_AT_BANANA_REPR, inez.query(CECILE_LOOKS_AT_BANANA_REPR));
		TestUtil.assertStreamEquals(
				Set.of(cecile_eats_banana, ceclie_looks_at_banana), inez.query("{cecile} {{$?} {banana}}"));

		Bridi looks_at_banana =
				inez.findById(ceclie_looks_at_banana.references().get(2)).get();
		Bridi looks_at = inez.findById(looks_at_banana.references().get(1)).get();
		assertEquals("looks at", looks_at.representation());
	}

	private Bridi assertGotTheBridi(final String expected, final Stream<Bridi> actual) {
		List<Bridi> actualList = actual.toList();
		assertEquals(1, actualList.size());
		Bridi bridi = actualList.get(0);
		assertEquals(expected, bridi.representation());
		return bridi;
	}
}
