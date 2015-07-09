package com.quakearts.webapp.logging.abstraction;

public enum WebListenerLevel {
	 FATAL,ERROR,WARN,INFO,DEBUG,TRACE;

	public boolean isGreaterOrEqual(WebListenerLevel threshold) {
		return this.ordinal()<=threshold.ordinal();
	}
}
