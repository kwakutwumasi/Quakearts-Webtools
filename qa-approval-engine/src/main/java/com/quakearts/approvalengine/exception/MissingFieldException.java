package com.quakearts.approvalengine.exception;

public class MissingFieldException extends ApprovalProcessingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 806105214066923285L;

	public MissingFieldException(String message, Object... parameters) {
		super(message, parameters);
	}
}
