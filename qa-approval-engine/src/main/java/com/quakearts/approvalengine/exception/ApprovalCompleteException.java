package com.quakearts.approvalengine.exception;

public class ApprovalCompleteException 
	extends ApprovalProcessingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4321581320470872331L;

	public ApprovalCompleteException(String message, Object... parameters) {
		super(message, parameters);
	}
}
