package com.quakearts.tools.test.mockserver.exception;

public class MockServerRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3979456786819928413L;

	public MockServerRuntimeException() {
		super();
	}

	public MockServerRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public MockServerRuntimeException(String message) {
		super(message);
	}

	public MockServerRuntimeException(Throwable cause) {
		super(cause);
	}

}
