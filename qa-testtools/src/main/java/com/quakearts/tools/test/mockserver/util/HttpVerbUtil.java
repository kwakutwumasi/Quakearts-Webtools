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
package com.quakearts.tools.test.mockserver.util;

import java.util.HashSet;
import java.util.Set;

public class HttpVerbUtil {
	private static final String PATCH = "PATCH";
	private static final String OPTIONS = "OPTIONS";
	private static final String TRACE = "TRACE";
	private static final String DELETE = "DELETE";
	private static final String PUT = "PUT";
	private static final String POST = "POST";
	private static final String HEAD = "HEAD";
	private static final String GET = "GET";
	private static final String CONNECT = "CONNECT";

	private HttpVerbUtil() {
	}
	
	private static Set<String> VALID_HTTP_VERBS = new HashSet<>(),
			REQUIRES_OUTPUT = new HashSet<>(),
			CAN_DO_OUTPUT = new HashSet<>();
	
	static {
		VALID_HTTP_VERBS.add(GET);
		VALID_HTTP_VERBS.add(HEAD);
		VALID_HTTP_VERBS.add(POST);
		VALID_HTTP_VERBS.add(PUT);
		VALID_HTTP_VERBS.add(DELETE);
		VALID_HTTP_VERBS.add(CONNECT);
		VALID_HTTP_VERBS.add(TRACE);
		VALID_HTTP_VERBS.add(OPTIONS);
		VALID_HTTP_VERBS.add(PATCH);
		
		REQUIRES_OUTPUT.add(POST);
		REQUIRES_OUTPUT.add(PUT);
		REQUIRES_OUTPUT.add(PATCH);
		REQUIRES_OUTPUT.add(CONNECT);
		
		CAN_DO_OUTPUT.add(GET);
		CAN_DO_OUTPUT.add(OPTIONS);
	}
	
	public static boolean isValidVerb(String httpMethod) {
		return VALID_HTTP_VERBS.contains(httpMethod.toUpperCase().trim());
	}
	
	public static boolean optionalOutputMethodsInlude(String httpMethod) {
		return CAN_DO_OUTPUT.contains(httpMethod.toUpperCase().trim());
	}
	
	public static boolean requiringOutputMethodsInclude(String httpMethod) {
		return REQUIRES_OUTPUT.contains(httpMethod.toUpperCase().trim());
	}
	
	public static boolean returningInputMethodsInclude(String httpMethod) {
		return !HEAD.equalsIgnoreCase(httpMethod.trim());
	}
}
