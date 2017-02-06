package com.quakearts.common.exceptionhandler.exceptions;

public class ExceptionHandlerRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6270157539155148781L;

	public ExceptionHandlerRuntimeException() {
		super();
	}

	public ExceptionHandlerRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExceptionHandlerRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExceptionHandlerRuntimeException(String message) {
		super(message);
	}

	public ExceptionHandlerRuntimeException(Throwable cause) {
		super(cause);
	}
}
