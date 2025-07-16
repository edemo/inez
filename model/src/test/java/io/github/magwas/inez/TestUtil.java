package io.github.magwas.inez;

import java.util.HashSet;
import java.util.Set;

public class TestUtil {
	public static <T> void diffCollections(Set<T> expected, Set<T> actual) {
		Set<T> remaining = new HashSet<T>();
		remaining.addAll(expected);
		remaining.removeAll(actual);
		System.out.println("expected - actual:");
		remaining.forEach(x -> System.out.println(x));

		actual.removeAll(expected);
		System.out.println("actual - expected:");
		actual.forEach(x -> System.out.println(x));

	}

}
