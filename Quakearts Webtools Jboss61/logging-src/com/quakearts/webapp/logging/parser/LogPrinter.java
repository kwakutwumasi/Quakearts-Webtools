package com.quakearts.webapp.logging.parser;

import javax.servlet.ServletConfig;

public interface LogPrinter {
	public void setupParser(ServletConfig config);
	public String format(String log, String contentType, String filter) throws UnsupportedOperationException, IllegalArgumentException;
	public byte[] format(byte[] log, String contentType, String filter) throws UnsupportedOperationException, IllegalArgumentException;
}
