package com.quakearts.approvalengine.exception;

public class InvalidApprovalProcessException extends ApprovalProcessingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5095563989502415577L;

	public InvalidApprovalProcessException() {
		super("The provided approvalProcess is not valid");
	}
}
