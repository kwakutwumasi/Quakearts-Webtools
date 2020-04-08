package com.quakearts.approvalengine.exception;

public class InvalidApprovalException extends ApprovalProcessingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 429545767931586462L;
	
	public InvalidApprovalException(String message, Object... parameters) {
		super(message, parameters);
	}
}
