package io.github.magwas.inez.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LogUtil {
	static Set<String> debuggedFiles = null;
	// Set.of("CreateBridiFromSumti.java");

	public static void debug(Object... args) {
		if (debuggedFiles == null)
			return;
		StackTraceElement stackTraceElement = Thread.currentThread()
				.getStackTrace()[2];
		String fileName = stackTraceElement.getFileName();
		if (!debuggedFiles.contains(fileName))
			return;
		List<String> params = List.of(args).stream().map(x -> x.toString())
				.collect(Collectors.toList());
		StringBuilder builder = new StringBuilder();
		builder.append("DEBUG ");
		builder.append(fileName);
		builder.append(":");
		builder.append(stackTraceElement.getLineNumber());
		builder.append(" ");
		builder.append(String.join(",", params));
		System.out.println(builder);
	}
}
