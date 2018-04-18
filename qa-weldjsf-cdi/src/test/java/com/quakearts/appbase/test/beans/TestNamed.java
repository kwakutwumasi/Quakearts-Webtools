/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.appbase.test.beans;

import javax.inject.Inject;
import javax.inject.Named;

import com.quakearts.appbase.test.experiments.TestInject;

@Named("testNamed")
public class TestNamed {
	
	private static boolean beanAccessed;
	
	@Inject
	private TestInject testInject;
	
	public String getResponse() {
		testInject.sayHello();
		testInject.testTransaction();
		return "OK";
	}
	
	public static boolean beanHasBeenAccessed() {
		return beanAccessed;
	}
}
