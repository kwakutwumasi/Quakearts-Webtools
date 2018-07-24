/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.security.rest.exception;

/**Exception thrown when the authentication cache experiences an error
 * @author kwakutwumasi-afriyie
 *
 */
public class AuthenticationCacheServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -267676554626808077L;

	public AuthenticationCacheServiceException() {
	}

	public AuthenticationCacheServiceException(String message) {
		super(message);
	}

	public AuthenticationCacheServiceException(Throwable cause) {
		super(cause);
	}

	public AuthenticationCacheServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationCacheServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
