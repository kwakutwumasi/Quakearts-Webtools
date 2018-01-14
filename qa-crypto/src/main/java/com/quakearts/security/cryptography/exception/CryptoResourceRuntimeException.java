package com.quakearts.security.cryptography.exception;

public class CryptoResourceRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3080120733866163653L;

	public CryptoResourceRuntimeException() {
	}

	public CryptoResourceRuntimeException(String message) {
		super(message);
	}

	public CryptoResourceRuntimeException(Throwable cause) {
		super(cause);
	}

	public CryptoResourceRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CryptoResourceRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
