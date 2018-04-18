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

public interface ProcessingContext {
	HttpRequest getHttpRequest() throws MockServerProcessingException;
	void sendHttpError(int errorCode) throws MockServerProcessingException;
	void sendHttpError(int errorCode, String message) throws MockServerProcessingException;
	void addHeader(String name, String value) throws MockServerProcessingException;
	void writeToOutput(int httpCode, String outputToWrite) throws MockServerProcessingException;
	void sendResponse(HttpResponse response) throws MockServerProcessingException;
	boolean responseSent();
}
