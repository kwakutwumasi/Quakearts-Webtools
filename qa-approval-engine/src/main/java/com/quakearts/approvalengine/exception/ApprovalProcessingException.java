package com.quakearts.approvalengine.exception;

import static java.text.MessageFormat.format;

public class ApprovalProcessingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6157788339894786110L;
	
	public ApprovalProcessingException(String message, Object... parameters) {
		super(format(message, parameters));
	}
	
}
