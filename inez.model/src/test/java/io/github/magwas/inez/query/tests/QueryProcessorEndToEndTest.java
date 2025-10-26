package io.github.magwas.inez.query.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.InezImpl;
import io.github.magwas.inez.parse.tests.InputTestData;
import io.github.magwas.inez.query.GetServiceByNameService;
import io.github.magwas.inez.tests.BridiTestData;
import io.github.magwas.inez.tests.TestConfig;
import io.github.magwas.konveyor.runtime.LogUtil;
import io.github.magwas.konveyor.testing.TestUtil;

@Tag("end-to-end")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class QueryProcessorEndToEndTest implements QueryProcessorTestData, InputTestData, BridiTestData {

	@Autowired
	InezImpl inez;

	@BeforeEach
	void setUp() throws IOException {
		inez.initialize();
	}

	@Test
	void test1() {
		inez.create(TEST_TEXT).peek(x -> LogUtil.debug("created:" + x)).toList();
		assertEquals(List.of(ALICE), inez.findAllByRepresentation(ALICE_REPR).toList());
		assertQuery(Set.of(ALICE_REPR), ALICE_REPR);
		assertQuery(Set.of(ALICE_EATS_BANANA_REPR), ALICE_EATS_BANANA_REPR);
		assertQuery(
				Set.of(ALICE_EATS_BANANA_REPR, BOB_EATS_BANANA_REPR, CECILE_EATS_BANANA_REPR), WHO_EATS_BANANA_INPUT);
		assertQuery(Set.of(CECILE_EATS_BANANA_REPR, CECILE_LOOKS_AT_BANANA_REPR), CECILE_WHAT_BANANA_INPUT);
		assertQuery(Set.of(ALICE_EATS_BANANA_REPR, ALICE_EATS_CHIPS_REPR), ALICE_EATS_WHAT_INPUT);
		LogUtil.addDebuggedClass(GetServiceByNameService.class);
		assertQuery(Set.of(PUTTY_REPR), DO_SAVE_PUTTY_INPUT);
		assertEquals(1, inez.findAllByRepresentation(PUTTY_REPR).count());
		List<Bridi> putty = inez.findAllByRepresentation(PUTTY_REPR).toList();
		assertQuery(Set.of(OSGI_REPR, BITCH_REPR, IS_A_REPR, OSGI_IS_A_BITCH_REPR), DO_SAVE_OSGI_IS_A_BITCH_INPUT);
		assertEquals(1, inez.findAllByRepresentation(OSGI_REPR).count());
		assertEquals(1, inez.findAllByRepresentation(BITCH_REPR).count());
		assertEquals(1, putty.size());
	}

	private Set<Bridi> assertQuery(final Set<String> expected, final String query) {
		Set<Bridi> result = inez.query(query).collect(Collectors.toSet());
		Set<String> actual =
				result.stream().map(bridi -> bridi.representation()).collect(Collectors.toSet());
		if (!expected.equals(actual)) {
			System.out.println("actual:");
			actual.forEach(System.out::println);
			System.out.println("expected:");
			expected.forEach(System.out::println);
			TestUtil.diffCollections(expected, actual);
		}
		assertEquals(expected, actual);
		return result;
	}
}
