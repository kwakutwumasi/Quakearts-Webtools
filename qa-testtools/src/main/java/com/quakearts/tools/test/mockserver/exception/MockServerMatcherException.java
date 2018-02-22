package com.quakearts.tools.test.mockserver.exception;

public class MockServerMatcherException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5585161041823555L;

	public MockServerMatcherException() {
	}

	public MockServerMatcherException(String message) {
		super(message);
	}

	public MockServerMatcherException(Throwable cause) {
		super(cause);
	}

	public MockServerMatcherException(String message, Throwable cause) {
		super(message, cause);
	}

	public MockServerMatcherException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
