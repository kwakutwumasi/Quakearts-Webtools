package com.quakearts.approvalengine.exception;

public class DuplicateGroupNameException extends ApprovalProcessingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3312212741514853188L;

	public DuplicateGroupNameException() {
		super("The name has already been created for approvalGroup");
	}
}
