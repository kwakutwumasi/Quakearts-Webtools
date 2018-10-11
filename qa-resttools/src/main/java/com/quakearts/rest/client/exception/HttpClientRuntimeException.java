package com.quakearts.rest.client.exception;

public class HttpClientRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4971545116819148818L;

	public HttpClientRuntimeException(String message) {
		super(message);
	}

	public HttpClientRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
