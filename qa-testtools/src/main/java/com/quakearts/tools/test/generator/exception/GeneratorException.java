package com.quakearts.tools.test.generator.exception;

public class GeneratorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 171492263955879608L;

	public GeneratorException() {
	}

	public GeneratorException(String message) {
		super(message);
	}

	public GeneratorException(Throwable cause) {
		super(cause);
	}

	public GeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	public GeneratorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
