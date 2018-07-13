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
package com.quakearts.tools.test.mockserver.model;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;

/**Implemented by classes the hold HTTP messages (input or output)
 * @author kwakutwumasi-afriyie
 *
 */
public interface HttpMessage {
	/**Get the collection of headers values for the message
	 * @return the collection of headers values
	 */
	Collection<HttpHeader> getHeaders();
	/**Get the header value with the specified name. 
	 * This is a short cut method that only 
	 * returns the first header value encountered.
	 * @param name the name of the header value
	 * @return the header value, if present
	 */
	String getHeaderValue(String name);
	/**Get the header values with the specified name. 
	 * @param name the name of the header value
	 * @return a {@linkplain List} of header values, if present
	 */
	List<String> getHeaderValues(String name);
	/**Get the content encoding of the HTTP message body
	 * @return the content encoding
	 */
	String getContentEncoding();
	/**Get the content of the HTTP message
	 * @return the content
	 * @throws UnsupportedEncodingException if the platform does not support the encoding used
	 */
	String getContent() throws UnsupportedEncodingException;
	/**Get the content in raw byte form
	 * @return the content
	 */
	byte[] getContentBytes();
}