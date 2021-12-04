package com.quakearts.microservices.dockerinit.exception;

public class InitException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7708064307430548896L;

	public InitException() {
		super();
	}

	public InitException(String message, Throwable cause) {
		super(message, cause);
	}

	public InitException(String message) {
		super(message);
	}

	public InitException(Throwable cause) {
		super(cause);
	}

}
