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
package com.quakearts.webapp.orm.exception;

public class DataStoreException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1632965561461122564L;

	public DataStoreException() {
		super();
	}

	public DataStoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DataStoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataStoreException(String message) {
		super(message);
	}

	public DataStoreException(Throwable cause) {
		super(cause);
	}

}
