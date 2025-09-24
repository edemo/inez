package io.github.magwas.inez.parse;

public class IdUtil {
	public static String createID(final String reference) {
		// return reference;
		// return UUID.nameUUIDFromBytes(reference.getBytes()).toString();
		return "id:" + reference;
	}
}
