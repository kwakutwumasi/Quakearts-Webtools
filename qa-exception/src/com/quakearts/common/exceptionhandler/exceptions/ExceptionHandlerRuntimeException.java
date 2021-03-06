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
package com.quakearts.common.exceptionhandler.exceptions;

/**Thrown to indicate errors in configuring the exception handlers
 * @author kwakutwumasi-afriyie
 *
 */
public class ExceptionHandlerRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6270157539155148781L;

	public ExceptionHandlerRuntimeException() {
		super();
	}

	public ExceptionHandlerRuntimeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExceptionHandlerRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExceptionHandlerRuntimeException(String message) {
		super(message);
	}

	public ExceptionHandlerRuntimeException(Throwable cause) {
		super(cause);
	}
}
