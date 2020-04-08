package com.quakearts.approvalengine.exception;

public class DuplicateRuleException extends ApprovalProcessingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5528776860187993626L;

	public DuplicateRuleException(String message, Object... parameters) {
		super(message, parameters);
	}

}
