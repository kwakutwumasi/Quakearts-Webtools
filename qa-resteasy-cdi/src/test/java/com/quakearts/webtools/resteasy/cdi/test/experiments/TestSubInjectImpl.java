/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.webtools.resteasy.cdi.test.experiments;

public class TestSubInjectImpl implements TestSubInject {

	private static boolean didSomething;
	
	@Override
	public void doSomething() {
		didSomething = true;
	}

	public static boolean hasDoneSomething() {
		return didSomething;
	}
	
	public static void reset() {
		didSomething = false;
	}
}
