package com.quakearts.webapp.orm.cdi.exception;

public class NoTransactionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7243911587008803736L;

	public NoTransactionException(String message) {
		super(message);
	}
}
