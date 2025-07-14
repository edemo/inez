package io.github.magwas.inez;

import java.util.ArrayList;
import java.util.Collection;

public class TestUtil {
	public static <T> void diffCollections(Collection<T> expected,
			Collection<T> actual) {
		Collection<T> remaining = new ArrayList<>(expected);
		remaining.removeAll(actual);
		System.out.println("expected - actual:");
		remaining.forEach(x -> System.out.println(x));

		Collection<T> remaining2 = new ArrayList<>(actual);
		remaining2.removeAll(expected);
		System.out.println("actual - expected:");
		remaining2.forEach(x -> System.out.println(x));

	}

}
