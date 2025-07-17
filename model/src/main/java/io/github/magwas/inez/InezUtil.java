package io.github.magwas.inez;

public class InezUtil {
	public static String createID(String reference) {
		// return reference;
		// return UUID.nameUUIDFromBytes(reference.getBytes()).toString();
		return "id:" + reference;
	}

}
