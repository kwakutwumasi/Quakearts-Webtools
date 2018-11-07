package com.quakearts.appbase.internal.json;

public class WriterConfigs {
	private WriterConfigs() {}
	/**
	 * Write JSON in its minimal form, without any additional whitespace. This is
	 * the default.
	 */
	public static final WriterConfig MINIMAL =JsonWriter::new;

	/**
	 * Write JSON in pretty-print, with each value on a separate line and an
	 * indentation of two spaces.
	 */
	public static final WriterConfig PRETTY_PRINT = PrettyPrint.indentWithSpaces(2);

}
