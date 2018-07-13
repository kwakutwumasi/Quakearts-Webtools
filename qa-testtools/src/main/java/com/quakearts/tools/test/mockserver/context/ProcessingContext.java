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
package com.quakearts.tools.test.mockserver.context;

import com.quakearts.tools.test.mockserver.exception.MockServerProcessingException;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.model.HttpResponse;

/**Context object for processing HTTP requests and responses
 * @author kwakutwumasi-afriyie
 *
 */
public interface ProcessingContext {
	/**The {@linkplain HttpRequest} being processed
	 * @return the {@linkplain HttpRequest}
	 * @throws MockServerProcessingException if there is a problem retrieving the request
	 */
	HttpRequest getHttpRequest() throws MockServerProcessingException;
	/**Send an HTTP error response.
	 * @param errorCode the HTTP error code to use
	 * @throws MockServerProcessingException if there is a problem sending the response
	 */
	void sendHttpError(int errorCode) throws MockServerProcessingException;
	/**Send an HTTP error response with a message
	 * @param errorCode the HTTP error code to use
	 * @param message the message to send
	 * @throws MockServerProcessingException if there is a problem sending the response
	 */
	void sendHttpError(int errorCode, String message) throws MockServerProcessingException;
	/**Add an HTTP header
	 * @param name the header name
	 * @param value the header value
	 * @throws MockServerProcessingException if there is a problem setting the header
	 */
	void addHeader(String name, String value) throws MockServerProcessingException;
	/**Write the output as part of the response
	 * @param httpCode the HTTP response code to use
	 * @param outputToWrite the output to write
	 * @throws MockServerProcessingException if there is a problem writing the output
	 */
	void writeToOutput(int httpCode, String outputToWrite) throws MockServerProcessingException;
	/**Send the {@linkplain HttpResponse}
	 * @param response the {@linkplain HttpResponse} to send
	 * @throws MockServerProcessingException if there is a problem processing the response
	 */
	void sendResponse(HttpResponse response) throws MockServerProcessingException;
	/**Determine if a response has been sent. An exception will be thrown 
	 * if an attempt is made to send a response after a response has been committed
	 * @return true if a response has already been sent
	 */
	boolean responseSent();
}
