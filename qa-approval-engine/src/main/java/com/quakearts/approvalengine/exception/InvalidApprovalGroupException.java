package com.quakearts.approvalengine.exception;

public class InvalidApprovalGroupException extends ApprovalProcessingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5795099675975509470L;

	public InvalidApprovalGroupException() {
		super("The provided approvalGroup is not valid");
	}
	
}
