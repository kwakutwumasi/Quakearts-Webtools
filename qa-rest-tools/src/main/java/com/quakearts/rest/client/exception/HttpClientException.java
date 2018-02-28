package com.quakearts.rest.client.exception;

public class HttpClientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1919214268898218420L;

	public HttpClientException() {
	}

	public HttpClientException(String message) {
		super(message);
	}

	public HttpClientException(Throwable cause) {
		super(cause);
	}

	public HttpClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
