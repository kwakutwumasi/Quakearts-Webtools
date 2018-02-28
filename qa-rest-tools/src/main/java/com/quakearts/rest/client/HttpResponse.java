package com.quakearts.rest.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public String getOutput() {
		return output;
	}

	public String getMessage() {
		return message;
	}

	public int getHttpCode() {
		return httpCode;
	}
	
	public Map<String, List<String>> getHeaders() {
		return headers;
	}
	
	public List<String> getHeaderList(String headerName){
		return headers.get(headerName);
	}
	
	public String getHeader(String headerName){
		List<String> headerFields = headers.get(headerName);
		return headerFields != null && !headerFields.isEmpty()? headerFields.iterator().next():null;
	}
}