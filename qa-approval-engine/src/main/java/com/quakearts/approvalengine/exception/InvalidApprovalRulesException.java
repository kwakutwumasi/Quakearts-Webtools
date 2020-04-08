package com.quakearts.approvalengine.exception;

public class InvalidApprovalRulesException extends ApprovalProcessingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7689580416733678060L;

	public InvalidApprovalRulesException(String message, Object... parameters) {
		super(message, parameters);
	}
	
}
