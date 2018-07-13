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
package com.quakearts.tools.test.mockserver.exception;

/**Exception thrown when an error occurs during mock server request/response processing
 * @author kwakutwumasi-afriyie
 *
 */
public class MockServerProcessingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4814088889015530754L;

	public MockServerProcessingException() {
	}

	public MockServerProcessingException(String message) {
		super(message);
	}

	public MockServerProcessingException(Throwable cause) {
		super(cause);
	}

	public MockServerProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

	public MockServerProcessingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
