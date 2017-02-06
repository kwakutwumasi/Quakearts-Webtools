package com.quakearts.classpathscanner.exception;

public class ScannerRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7372531965898980265L;

	public ScannerRuntimeException() {
		super();
	}

	public ScannerRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ScannerRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ScannerRuntimeException(String message) {
		super(message);
	}

	public ScannerRuntimeException(Throwable cause) {
		super(cause);
	}

}
