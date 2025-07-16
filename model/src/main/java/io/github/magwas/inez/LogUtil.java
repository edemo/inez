package io.github.magwas.inez;

import java.util.List;
import java.util.stream.Collectors;

public class LogUtil {
	public static void debug(Object... args) {
		List<String> params = List.of(args).stream().map(x -> x.toString())
				.collect(Collectors.toList());
		System.out.println("DEBUG " + String.join(",", params));
	}
}
