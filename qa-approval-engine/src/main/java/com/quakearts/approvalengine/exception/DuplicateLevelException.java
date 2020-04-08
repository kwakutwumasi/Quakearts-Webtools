package com.quakearts.approvalengine.exception;

public class DuplicateLevelException extends ApprovalProcessingException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4001459888861168542L;

	public DuplicateLevelException() {
		super("The level has already been created");
	}
}
