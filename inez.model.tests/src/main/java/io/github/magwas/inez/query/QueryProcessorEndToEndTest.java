package io.github.magwas.inez.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.magwas.inez.Bridi;
import io.github.magwas.inez.BridiTestData;
import io.github.magwas.inez.InezImpl;
import io.github.magwas.inez.TestConfig;
import io.github.magwas.inez.functions.Save;
import io.github.magwas.inez.osgi.SpringBootBundleActivator;
import io.github.magwas.runtime.LogUtil;
import io.github.magwas.testing.TestUtil;

@Tag("end-to-end")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class QueryProcessorEndToEndTest implements BridiTestData {

	@Autowired
	InezImpl inez;

	@BeforeEach
	void setUp() throws IOException {
		ServiceReference<Save> sr = mock(ServiceReference.class);
		BundleContext ctx = mock(BundleContext.class);
		when((ServiceReference<Save>) ctx.getServiceReference("io.github.magwas.inez.functions.Save"))
				.thenReturn(sr);
		when(ctx.getService(sr)).thenReturn(new Save());
		SpringBootBundleActivator.bundleContext = ctx;
		inez.initialize();
	}

	@Test
	void test1() {
		inez.create(TEST_TEXT).peek(x -> LogUtil.debug("created:" + x)).toList();
		assertEquals(List.of(ALICE), inez.findAllByRepresentation(ALICE_REPR).toList());
		assertQuery(Set.of(ALICE_REPR), ALICE_REPR);
		assertQuery(Set.of(ALICE_EATS_BANANA_REPR), ALICE_EATS_BANANA_REPR);
		assertQuery(
				Set.of(ALICE_EATS_BANANA_REPR, BOB_EATS_BANANA_REPR, CECILE_EATS_BANANA_REPR),
				"{$?} {{eats} {banana}}");
		assertQuery(Set.of(CECILE_EATS_BANANA_REPR, CECILE_LOOKS_AT_BANANA_REPR), "{cecile} {{$?} {banana}}");
		assertQuery(Set.of(ALICE_EATS_BANANA_REPR, ALICE_EATS_CHIPS_REPR), "{alice} {{eats} {$?}}");
		assertQuery(Set.of("putty"), "doSave {" + "putty" + "}");
		assertEquals(1, inez.findAllByRepresentation("putty").count());
		List<Bridi> putty = inez.findAllByRepresentation("putty").toList();
		assertQuery(
				Set.of("osgi", "bitch", "{0} is a {1}", "{osgi} is a {bitch}"),
				"doSave {" + "{osgi} is a {bitch}" + "}");
		assertEquals(1, inez.findAllByRepresentation("osgi").count());
		assertEquals(1, inez.findAllByRepresentation("bitch").count());
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
