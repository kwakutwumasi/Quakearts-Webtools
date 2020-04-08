package com.quakearts.approvalengine.exception;

public class InvalidApproverException extends ApprovalProcessingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3306999484646497650L;

	public InvalidApproverException() {
		super("The specified approver is not valid");
	}
	
}
