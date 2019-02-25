package com.quakearts.utilities.test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;

public class TestFormatter extends Formatter {

	private static final String DEFAULTFORMAT = "[%1$30s] [%2$010d] [%3$td-%3$tm-%3$tY %3$tH:%3$tM:%3$tS.%3$tL]"
			+ "[%4$-8s] [Thread-%8$010d] [%5$-50s] [%6$-20s]\r\n%7$s\r\n";
	
	@Override
	public synchronized String format(LogRecord record) {
		Date logDate = new Date(record.getMillis());
		String thrownError = formatThrown(record.getThrown());
		
		return String.format(getFormat(), 
				record.getLoggerName(),
				record.getSequenceNumber(),
				logDate,
				record.getLevel(),
				record.getSourceClassName(),
				record.getSourceMethodName(),
				record.getMessage()+thrownError,
				record.getThreadID());
	}

	private String getFormat() {
		String format = LogManager.getLogManager().getProperty(TestFormatter.class.getName()+".format");
		return format!=null?format:DEFAULTFORMAT;
	}

	private String formatThrown(Throwable thrown) {
		if(thrown!=null) {
			StringWriter stream = new StringWriter();
			thrown.printStackTrace(new PrintWriter(stream));
			return "\r\n"+stream.toString();
		}
		return "";
	}
}
