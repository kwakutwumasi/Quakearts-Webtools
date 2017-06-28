package com.quakearts.webapp.security.jwt.exception;

public class JWTException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3050747183309908628L;

	public JWTException() {
	}

	public JWTException(String message) {
		super(message);
	}

	public JWTException(Throwable cause) {
		super(cause);
	}

	public JWTException(String message, Throwable cause) {
		super(message, cause);
	}

	public JWTException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
