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
package com.quakearts.utils.messagebroker.exception;

/**Thrown when there is a problem exchanging the messages
 * @author kwakutwumasi-afriyie
 *
 */
public class MessageBrokerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6998762722602812694L;

	public MessageBrokerException() {
		super();
	}

	public MessageBrokerException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageBrokerException(String message) {
		super(message);
	}

	public MessageBrokerException(Throwable cause) {
		super(cause);
	}

}
