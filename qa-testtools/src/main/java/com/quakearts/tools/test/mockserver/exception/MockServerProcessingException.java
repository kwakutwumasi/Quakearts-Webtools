package com.quakearts.tools.test.mockserver.exception;

public class MockServerProcessingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4814088889015530754L;

	public MockServerProcessingException() {
	}

	public MockServerProcessingException(String message) {
		super(message);
	}

	public MockServerProcessingException(Throwable cause) {
		super(cause);
	}

	public MockServerProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

	public MockServerProcessingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
