package com.quakearts.approvalengine.exception;

public class NotFoundException extends ApprovalProcessingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -26970738071795843L;

	public NotFoundException(String message, Object... parameters) {
		super(message, parameters);
	}
}
