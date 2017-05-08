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
package com.quakearts.syshub.core.impl;
import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.exception.ProcessingException;

import java.io.Serializable;

/**Core object for storing messages and recording message status.
 * @author Kwaku Twumasi-Afriyie
 *
 */
public final class MessageByteImpl extends ByteMessageBase
	implements Serializable, Message<byte[]> {

	private static final long serialVersionUID = 5563271783697520715L;

	public MessageByteImpl() {
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#addRecipient(java.lang.String)
	 */
	@Override
	public Message<byte[]> addRecipient(String newrecipient){
		addRecipientToList(newrecipient);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#appendHeader(java.lang.String)
	 */
	@Override
	public Message<byte[]> appendHeader(byte[] header) throws ProcessingException {
		appendHeader(header);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#appendBody(java.lang.String)
	 */
	@Override
	public Message<byte[]> appendBody(byte[] body) throws ProcessingException {
		appendBody(body);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#appendFooter(java.lang.String)
	 */
	@Override
	public Message<byte[]> appendFooter(byte[] footer) throws ProcessingException {
		appendFooter(footer);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#addProperty(java.lang.String, java.io.Serializable)
	 */
	@Override
	public Message<byte[]> addProperty(String name, Serializable prop){
		addPropertyForMessage(name,prop);
		return this;
	}

	@Override
	public <M> M getMessageData(Class<M> expectedType) throws ProcessingException {
		if(expectedType.isAssignableFrom(byte[].class))
			return expectedType.cast(getBytes());
		else
			throw new ProcessingException("Unexpected type: "+expectedType.getName()+". Message class produces byte[]");
	}
}
