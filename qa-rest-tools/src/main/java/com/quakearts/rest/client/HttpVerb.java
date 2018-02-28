package com.quakearts.rest.client;

import java.util.HashSet;
import java.util.Set;

public enum HttpVerb {
	PATCH,
	OPTIONS,
	TRACE,
	DELETE,
	PUT,
	POST,
	HEAD,
	GET,
	CONNECT;
	
	private static Set<HttpVerb>
			REQUIRES_OUTPUT = new HashSet<>(),
			CAN_DO_OUTPUT = new HashSet<>();
	
	static {
		REQUIRES_OUTPUT.add(POST);
		REQUIRES_OUTPUT.add(PUT);
		REQUIRES_OUTPUT.add(PATCH);
		REQUIRES_OUTPUT.add(CONNECT);
		
		CAN_DO_OUTPUT.add(GET);
		CAN_DO_OUTPUT.add(OPTIONS);
	}
		
	public static boolean optionalOutputMethodsInlude(HttpVerb httpMethod) {
		return CAN_DO_OUTPUT.contains(httpMethod);
	}
	
	public static boolean requiringOutputMethodsInclude(HttpVerb httpMethod) {
		return REQUIRES_OUTPUT.contains(httpMethod);
	}
	
	public static boolean returningInputMethodsInclude(HttpVerb httpMethod) {
		return HEAD != httpMethod;
	}
}
