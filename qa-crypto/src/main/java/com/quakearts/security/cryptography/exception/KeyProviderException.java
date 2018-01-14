package com.quakearts.security.cryptography.exception;

public class KeyProviderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7095774065134639549L;

	public KeyProviderException() {
	}

	public KeyProviderException(String message) {
		super(message);
	}

	public KeyProviderException(Throwable cause) {
		super(cause);
	}

	public KeyProviderException(String message, Throwable cause) {
		super(message, cause);
	}

	public KeyProviderException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
