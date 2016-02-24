package com.quakearts.webapp.logging.abstraction;

public class WebListenerLoggingEvent {

	private WebListenerLevel level;
	private String loggerName;
	private String renderedMessage;
	private String threadName;
	private long timeStamp;
	
	public WebListenerLoggingEvent(WebListenerLevel level, String loggerName,
			String renderedMessage, String threadName, long timeStamp) {
		this.level = level;
		this.loggerName = loggerName;
		this.renderedMessage = renderedMessage;
		this.threadName = threadName;
		this.timeStamp = timeStamp;
	}

	public WebListenerLevel getLevel() {
		return level;
	}

	public String getLoggerName() {
		return loggerName;
	}

	public String getRenderedMessage() {
		return renderedMessage;
	}

	public String getThreadName() {
		return threadName;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

}
