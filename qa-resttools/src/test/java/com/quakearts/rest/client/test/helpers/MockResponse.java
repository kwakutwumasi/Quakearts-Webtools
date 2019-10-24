package com.quakearts.rest.client.test.helpers;

public class MockResponse {
	private String data;
	private int returnCode;
	
	public MockResponse(String data, int returnCode) {
		this.data = data;
		this.returnCode = returnCode;
	}
	
	public String getData() {
		return data;
	}
	
	public int getReturnCode() {
		return returnCode;
	}
}