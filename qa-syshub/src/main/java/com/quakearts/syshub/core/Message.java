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
package com.quakearts.syshub.core;

import java.io.IOException;
import java.io.Serializable;

import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;

/**Interface implemented by messages
 * @author kwakutwumasi-afriyie
 *
 * @param <T>
 */
public interface Message<T> extends Serializable {

	public Message<T> addRecipient(String newrecipient);
	/**Get the list of recipients for this message;
	 * @return All recipients of this message;
	 */
	String[] getRecipients();

	/**Append datatype to the header
	 * @param header
	 * @return This object for method chaining
	 * @throws ProcessingException
	 */
	Message<T> appendHeader(T t) throws ProcessingException;

	/**Append bytes to the body
	 * @param bytes
	 * @return This object for method chaining
	 * @throws ProcessingException
	 */
	Message<T> appendBody(T t) throws ProcessingException;

	/**Append bytes to the footer
	 * @param bytes
	 * @return This object for method chaining
	 * @throws ProcessingException
	 */
	Message<T> appendFooter(T t) throws ProcessingException;

	/**Fetches the completed message for sending. This operation sets the message
	 * as read-only. Attempts to write to the header, body or footer will result 
	 * in a NotificationEception
	 * @return The message object as required by the messenger
	 * @throws IOException
	 */
	<M>M getMessageData(Class<M> expectedType) throws ProcessingException;

	/**Add properties specific to this message. These properties may be set by 
	 * formatters that need to pass additional information to the messenger.
	 * @param name
	 * @param prop
	 * @return
	 */
	Message<T> addProperty(String name, Serializable prop);

	/** Get a property set on this message
	 * @param name
	 * @return
	 */
	Serializable getProperty(String name);

	/**
	 * @return Length of this message in bytes
	 */
	int length();

	String toString();

	/**
	 * @return The unique id of the message. The id is unique to each instance
	 */
	String getId();
	/**Convenience method to mark message for redaction when viewed on the log console
	 * 
	 * @param secure
	 */
	void setSecure(boolean secure);

	/**Indicate the message is secured and will display redacted content
	 * @return
	 */
	boolean isSecure();

	/**Return the status of the message. This method blocks until the status has been set
	 * @return
	 * @throws InterruptedException
	 */
	<S> S getMessageStatus(Class<S> expectedType) throws InterruptedException, ConfigurationException;

	/**Set the status of the message, and notify all threads waiting for the status of the message
	 * @param messageStatus
	 */
	void setMessageStatus(Object messageStatus);

}
