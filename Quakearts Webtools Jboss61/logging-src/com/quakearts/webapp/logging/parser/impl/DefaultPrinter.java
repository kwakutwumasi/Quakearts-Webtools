package com.quakearts.webapp.logging.parser.impl;

import javax.servlet.ServletConfig;

import com.quakearts.webapp.logging.parser.LogPrinter;

public class DefaultPrinter implements LogPrinter {

	@Override
	public void setupParser(ServletConfig config) {
	}

	@Override
	public String format(String log, String contentType, String filter)
			throws UnsupportedOperationException, IllegalArgumentException {
		return log;
	}

	@Override
	public byte[] format(byte[] log, String contentType,String filter)
			throws UnsupportedOperationException, IllegalArgumentException {
		return log;
	}

}
