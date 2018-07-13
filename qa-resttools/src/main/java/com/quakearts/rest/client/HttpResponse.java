/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie (kwaku.twumasi@quakearts.com)
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.rest.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Response object containing the results of the HTTP call
 * @author kwakutwumasi-afriyie
 *
 */
public class HttpResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -573510179858452031L;
	private String output;
	private String message;
	private int httpCode;
	private Map<String, List<String>> headers = new HashMap<>();
	
	HttpResponse(String output, String message, int httpCode, Map<String, List<String>> headers) {
		this.output = output;
		this.message = message;
		this.httpCode = httpCode;
		if(headers!=null)
			this.headers = headers;
	}

	/**Getter for the output read from the http call
	 * @return the output
	 */
	public String getOutput() {
		return output;
	}

	/**getter for the HTTP message
	 * @return the HTTP message
	 */
	public String getMessage() {
		return message;
	}

	/**getter for the HTTP response code
	 * @return the HTTP response code
	 */
	public int getHttpCode() {
		return httpCode;
	}
	
	/**Getter for the HTTP response headers 
	 * @return the HTTP response headers
	 */
	public Map<String, List<String>> getHeaders() {
		return headers;
	}
	
	/**Get the list of header values with the given name 
	 * @param headerName the header name
	 * @return the list of header values 
	 */
	public List<String> getHeaderList(String headerName){
		return headers.get(headerName);
	}
	
	/**Get the header value with the given name 
	 * @param headerName the header name
	 * @return the header value
	 */
	public String getHeader(String headerName){
		List<String> headerFields = headers.get(headerName);
		return headerFields != null && !headerFields.isEmpty()? headerFields.iterator().next():null;
	}
}
