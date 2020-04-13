package com.quakearts.approvalengine.exception;

import static java.text.MessageFormat.format;

public class MissingFieldException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 806105214066923285L;

	public MissingFieldException(String message, Object... parameters) {
		super(format(message, parameters));
	}
}
