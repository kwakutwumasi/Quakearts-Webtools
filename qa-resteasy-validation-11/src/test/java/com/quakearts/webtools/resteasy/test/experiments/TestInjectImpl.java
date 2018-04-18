/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.webtools.resteasy.test.experiments;

import com.quakearts.webtools.resteasy.validation.test.helpers.TestParameter;

public class TestInjectImpl implements TestInject {	
	private static boolean processedTestParameter;
	
	public static void reset() {
		processedTestParameter = false;
	}

	@Override
	public void processTestParameter(TestParameter parameter){
		processedTestParameter = true;
	}
	
	public static boolean processedTestParameter() {
		return processedTestParameter;
	}
}
