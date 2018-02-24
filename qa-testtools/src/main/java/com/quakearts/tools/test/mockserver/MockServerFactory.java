package com.quakearts.tools.test.mockserver;

import com.quakearts.tools.test.mockserver.impl.MockServerImpl;

public class MockServerFactory {
	protected static MockServerFactory instance;	
	private MockServerFactory() {
	}
	
	public static MockServerFactory getInstance() {
		if(instance == null)
			instance = new MockServerFactory();
		
		return instance;
	}
	
	public MockServer getMockServer() {
		return new MockServerImpl();
	}
}
