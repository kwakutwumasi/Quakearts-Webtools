package com.quakearts.approvalengine.exception;

public class InvalidCountException extends ApprovalProcessingException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 465223563504537541L;

	public InvalidCountException() {
		super("The approval count entered is invalid");
	}
}
