package com.quakearts.webapp.security.rest.exception;

public class SecurityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6965508286773459899L;

	public SecurityException() {
	}

	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(Throwable cause) {
		super(cause);
	}

	public SecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public SecurityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
