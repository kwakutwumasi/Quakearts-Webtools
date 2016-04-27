package com.quakearts.webapp.facelets.util;

public class WebPageBean {
	private int statusCode;
	private String responseMessage, content;

	public WebPageBean(String content, String responseMessage, int statusCode){
		this.content = content;
		this.responseMessage = responseMessage;
		this.statusCode = statusCode;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public String getContent() {
		return content;
	}
	
}
