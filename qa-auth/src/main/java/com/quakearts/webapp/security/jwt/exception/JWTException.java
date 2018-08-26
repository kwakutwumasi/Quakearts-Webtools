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
package com.quakearts.webapp.security.jwt.exception;

public class JWTException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3050747183309908628L;

	public JWTException() {
	}

	public JWTException(String message) {
		super(message);
	}

	public JWTException(Throwable cause) {
		super(cause);
	}

	public JWTException(String message, Throwable cause) {
		super(message, cause);
	}

	public JWTException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}