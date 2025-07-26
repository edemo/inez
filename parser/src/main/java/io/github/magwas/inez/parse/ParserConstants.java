package io.github.magwas.inez.parse;

public interface ParserConstants {
	String QUERY_BRIDI_ID = "$?";
	public static String createID(String reference) {
		// return reference;
		// return UUID.nameUUIDFromBytes(reference.getBytes()).toString();
		return "id:" + reference;
	}

}
