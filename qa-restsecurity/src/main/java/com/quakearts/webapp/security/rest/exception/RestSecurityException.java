package com.quakearts.webapp.security.rest.exception;

/**Exception thrown then there is a security error
 * @author kwakutwumasi-afriyie
 *
 */
public class RestSecurityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6965508286773459899L;

	public RestSecurityException() {
	}

	public RestSecurityException(String message) {
		super(message);
	}

	public RestSecurityException(Throwable cause) {
		super(cause);
	}

	public RestSecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestSecurityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
