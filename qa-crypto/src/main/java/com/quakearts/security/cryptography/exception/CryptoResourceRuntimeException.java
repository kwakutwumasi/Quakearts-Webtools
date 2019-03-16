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
package com.quakearts.security.cryptography.exception;

public class CryptoResourceRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3080120733866163653L;

	public CryptoResourceRuntimeException(Throwable cause) {
		super(cause);
	}

	public CryptoResourceRuntimeException(String message) {
		super(message);
	}
}
