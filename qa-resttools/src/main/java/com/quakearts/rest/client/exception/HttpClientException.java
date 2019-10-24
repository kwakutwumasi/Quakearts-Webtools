/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie (kwaku.twumasi@quakearts.com)
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.rest.client.exception;

/**Exception thrown when there is a problem processing the http request
 * @author kwakutwumasi-afriyie
 *
 */
public class HttpClientException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1919214268898218420L;

	public HttpClientException(String message) {
		super(message);
	}

	public HttpClientException(String message, Throwable cause) {
		super(message, cause);
	}
}
