/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.security.rest.exception;

public class SecurityContextException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -267676554626808077L;

	public SecurityContextException() {
	}

	public SecurityContextException(String message) {
		super(message);
	}

	public SecurityContextException(Throwable cause) {
		super(cause);
	}

	public SecurityContextException(String message, Throwable cause) {
		super(message, cause);
	}

	public SecurityContextException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
