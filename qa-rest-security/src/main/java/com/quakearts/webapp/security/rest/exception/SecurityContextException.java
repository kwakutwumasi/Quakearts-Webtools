package com.quakearts.webapp.security.rest.exception;

public class SecurityContextException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -267676554626808077L;

	public SecurityContextException() {
	}

	public SecurityContextException(String message) {
		super(message);
	}

	public SecurityContextException(Throwable cause) {
		super(cause);
	}

	public SecurityContextException(String message, Throwable cause) {
		super(message, cause);
	}

	public SecurityContextException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
