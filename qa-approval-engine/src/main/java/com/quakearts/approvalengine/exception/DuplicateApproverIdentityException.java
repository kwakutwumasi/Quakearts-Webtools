package com.quakearts.approvalengine.exception;

public class DuplicateApproverIdentityException extends ApprovalProcessingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6686059449927287244L;

	public DuplicateApproverIdentityException() {
		super("The externalId has already been created for approver in approvalGroup");
	}
}
