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
package com.quakearts.tools.test.mockserver.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quakearts.tools.test.mockserver.context.ProcessingContext;
import com.quakearts.tools.test.mockserver.exception.MockServerProcessingException;
import com.quakearts.tools.test.mockserver.model.HttpHeader;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.model.HttpResponse;
import com.quakearts.tools.test.mockserver.model.impl.HttpHeaderImpl;

import static com.quakearts.tools.test.mockserver.model.impl.HttpMessageBuilder.*;

class MockServletProcessingContextBuilder {
		
	static ProcessingContext createProcessingContext(HttpServletRequest req, HttpServletResponse res, String urlToMock) {
		MockServletProcessingContext context = new MockServletProcessingContext();
		context.req = req;
		context.res = res;
		context.urlToMock = urlToMock;
		
		return context;
	}
	
	static class MockServletProcessingContext implements ProcessingContext {
		HttpServletRequest req;
		HttpServletResponse res;
		HttpRequest httpRequest;
		String urlToMock;
		boolean responseSent;
		
		@Override
		public HttpRequest getHttpRequest() throws MockServerProcessingException {
			if(httpRequest == null) {
				HttpRequestBuilder requestBuilder = createNewHttpRequest();
				requestBuilder.setMethodAs(req.getMethod());
				String resource = req.getPathInfo()
						+(req.getQueryString() != null?"?"+req.getQueryString():"");
				requestBuilder.setResourceAs(resource);
				if(req.getCharacterEncoding()!=null)
					requestBuilder.setContentEncoding(req.getCharacterEncoding());
				
				requestBuilder.setId(req.getMethod()+"-"+urlToMock+(resource.startsWith("/")?"":"/")+resource);
				
				Enumeration<String> names = req.getHeaderNames();
				if(names != null)
					while (names.hasMoreElements()) {
						String headerName = names.nextElement();
						HttpHeaderImpl header = new HttpHeaderImpl();
						header.setName(headerName);
						Enumeration<String> values = req.getHeaders(headerName);
						while (values.hasMoreElements()) {
							String value = values.nextElement();
							header.addValue(value);
						}
						requestBuilder.addHeaders(header);
					}
				
				try {
					InputStream in = req.getInputStream();
						ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
						int read;
						while ((read=in.read())!=-1) {
							bos.write(read);
						}
					
					if(bos.size()>0)
						requestBuilder.setContentBytes(bos.toByteArray());
				} catch (IOException e) {
					throw new MockServerProcessingException("Unable to get content", e);
				}
				
				httpRequest = requestBuilder.thenBuild();
			}
			return httpRequest;
		}

		@Override
		public void sendHttpError(int errorCode) throws MockServerProcessingException {
			try {
				res.sendError(errorCode);
				responseSent = true;
			} catch (IOException e) {
				throw new MockServerProcessingException(e);
			}
		}

		@Override
		public void sendHttpError(int errorCode, String message) throws MockServerProcessingException {
			try {
				res.sendError(errorCode, message);
				responseSent = true;
			} catch (IOException e) {
				throw new MockServerProcessingException(e);
			}
		}

		@Override
		public void addHeader(String name, String value) {
			res.addHeader(name, value);
		}

		@Override
		public void writeToOutput(int httpCode, String outputToWrite) throws MockServerProcessingException {
			try {
				res.setStatus(httpCode);
				res.getWriter().append(outputToWrite);
				responseSent = true;
			} catch (IOException e) {
				throw new MockServerProcessingException(e);
			}
		}

		@Override
		public void sendResponse(HttpResponse response) throws MockServerProcessingException {
			res.setStatus(response.getResponseCode());
			for(HttpHeader header:response.getHeaders()) {
				for(String value:header.getValues())
					res.addHeader(header.getName(), value);
			}
			
			if(response.getContentBytes()!=null) {
				try {
					res.getOutputStream().write(response.getContentBytes());
					res.getOutputStream().flush();
				} catch (IOException e) {
					throw new MockServerProcessingException(e);
				}
			}
		}
		
		@Override
		public boolean responseSent() {
			return responseSent;
		}
	}
}
