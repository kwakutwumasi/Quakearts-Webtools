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

import java.util.HashSet;
import java.util.Set;

/**HTTP verbs to use in making the HTTP request
 * @author kwakutwumasi-afriyie
 *
 */
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
	
	private static Set<HttpVerb> requiresOutput = new HashSet<>();
	private static Set<HttpVerb> canDoOutput = new HashSet<>();
	
	static {
		requiresOutput.add(POST);
		requiresOutput.add(PUT);
		requiresOutput.add(PATCH);
		requiresOutput.add(CONNECT);
		
		canDoOutput.add(GET);
		canDoOutput.add(OPTIONS);
	}
		
	public static boolean optionalOutputMethodsInlude(HttpVerb httpMethod) {
		return canDoOutput.contains(httpMethod);
	}
	
	public static boolean requiringOutputMethodsInclude(HttpVerb httpMethod) {
		return requiresOutput.contains(httpMethod);
	}
	
	public static boolean returningInputMethodsInclude(HttpVerb httpMethod) {
		return HEAD != httpMethod;
	}
}
