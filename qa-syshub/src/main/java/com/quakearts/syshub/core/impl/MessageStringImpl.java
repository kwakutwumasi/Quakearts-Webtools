package com.quakearts.syshub.core.impl;
import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.exception.ProcessingException;

import java.io.Serializable;

/**Core object for storing messages and recording message status.
 * @author Kwaku Twumasi-Afriyie
 *
 */
public class MessageStringImpl extends ByteMessageBase implements Serializable, Message<String> {

	private static final long serialVersionUID = 5563271783697520715L;
	
	public MessageStringImpl() {
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#addRecipient(java.lang.String)
	 */
	@Override
	public Message<String> addRecipient(String newrecipient){
		addRecipientToList(newrecipient);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#appendHeader(java.lang.String)
	 */
	@Override
	public Message<String> appendHeader(String header) throws ProcessingException {
		appendHeaderBytes(header.getBytes());
		return this;
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#appendBody(java.lang.String)
	 */
	@Override
	public Message<String> appendBody(String body) throws ProcessingException {
		appendBodyBytes(body.getBytes());
		return this;
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#appendFooter(java.lang.String)
	 */
	@Override
	public Message<String> appendFooter(String footer) throws ProcessingException {
		appendFooterBytes(footer.getBytes());
		return this;
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#getBytes()
	 */
	@Override
	public <M> M getMessageData(Class<M> expectedType) throws ProcessingException {
		if(expectedType.isAssignableFrom(byte[].class))
			return expectedType.cast(getBytes());
		else if(expectedType.isAssignableFrom(String.class))
			return expectedType.cast(new String(getBytes()));
		else
			throw new ProcessingException("Unexpected type: "+expectedType.getName()+". Message class produces byte[]");
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.core.IMessage#addProperty(java.lang.String, java.io.Serializable)
	 */
	@Override
	public Message<String> addProperty(String name, Serializable prop){
		addPropertyForMessage(name,prop);		
		return this;
	}
}
