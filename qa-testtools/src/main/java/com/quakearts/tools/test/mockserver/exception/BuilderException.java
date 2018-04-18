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

public class BuilderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3979456786819928413L;

	public BuilderException() {
		super();
	}

	public BuilderException(String message, Throwable cause) {
		super(message, cause);
	}

	public BuilderException(String message) {
		super(message);
	}

	public BuilderException(Throwable cause) {
		super(cause);
	}

}
