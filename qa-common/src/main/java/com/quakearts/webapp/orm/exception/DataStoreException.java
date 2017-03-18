package com.quakearts.webapp.orm.exception;

public class DataStoreException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1632965561461122564L;

	public DataStoreException() {
		super();
	}

	public DataStoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataStoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataStoreException(String message) {
		super(message);
	}

	public DataStoreException(Throwable cause) {
		super(cause);
	}

}
