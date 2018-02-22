package com.quakearts.tools.test.mockserver.exception;

public class MockServerActionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5585161041823555L;

	public MockServerActionException() {
	}

	public MockServerActionException(String message) {
		super(message);
	}

	public MockServerActionException(Throwable cause) {
		super(cause);
	}

	public MockServerActionException(String message, Throwable cause) {
		super(message, cause);
	}

	public MockServerActionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
