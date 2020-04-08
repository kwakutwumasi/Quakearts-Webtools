package com.quakearts.approvalengine.exception;

public class IncompleteApprovalRulesException extends ApprovalProcessingException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6157591867875373062L;

	public IncompleteApprovalRulesException(String message, Object...parameters) {
		super(message, parameters);
	}

}
