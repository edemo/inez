package io.github.magwas.inez.impl;

import java.util.List;
import java.util.stream.Collectors;

public class LogUtil {
	public static void debug(Object... args) {
		List<String> params = List.of(args).stream().map(x -> x.toString())
				.collect(Collectors.toList());
		StackTraceElement stackTraceElement = Thread.currentThread()
				.getStackTrace()[2];
		StringBuilder builder = new StringBuilder();
		builder.append("DEBUG ");
		builder.append(stackTraceElement.getFileName());
		builder.append(":");
		builder.append(stackTraceElement.getLineNumber());
		builder.append(" ");
		builder.append(String.join(",", params));
		System.out.println(builder);
	}
}
