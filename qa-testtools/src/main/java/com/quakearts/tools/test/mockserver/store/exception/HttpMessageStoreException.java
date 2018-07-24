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
package com.quakearts.tools.test.mockserver.store.exception;

/**Exception thrown when there is an error with the HTTP message store
 * @author kwakutwumasi-afriyie
 *
 */
public class HttpMessageStoreException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8667964936753956967L;

	public HttpMessageStoreException() {
	}

	public HttpMessageStoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public HttpMessageStoreException(String message) {
		super(message);
	}

	public HttpMessageStoreException(Throwable cause) {
		super(cause);
	}

}
