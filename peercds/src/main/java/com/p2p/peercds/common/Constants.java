package com.p2p.peercds.common;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class Constants {

	public static final String BUCKET_NAME = "peer-cds";

	public static final String CLOUD_KEY = "cloud-key";

	/** Torrent file piece length (in bytes), we use 512 kB. */
	public static final int PIECE_LENGTH = 512 * 1024;

	public static final int PIECE_HASH_SIZE = 20;

	/** The query parameters encoding when parsing byte strings. */
	public static final String BYTE_ENCODING = "ISO-8859-1";

	@SuppressWarnings("unchecked")
	public static final Map<String, String> SPECIAL_TO_NSP_MAP = (Map) ImmutableMap
			.builder().put(",", "A").put(")", "B").put("(", "C").put("=", "D")
			.put("$", "E").put(":", "F").put(";", "G").put("&", "H")
			.put("#", "I").put(".", "J").put("/", "K").put("!", "L")
			.put("@", "M").put("*", "N").put("+", "O").put("%", "P")
			.put("^", "Q").put("<", "R").put(">", "S").put("_", "T")
			.put(" ", "U").put("[", "V").put("]", "W").put("{", "X")
			.put("}", "Y").build();

}