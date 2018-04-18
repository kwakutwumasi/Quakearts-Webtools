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

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class TestSubInjectDecorator implements TestSubInject {

	@Inject
	@Delegate
	@Any
	private TestSubInject inject;
	
	private static boolean decorated;
	
	@Override
	public void doSomething() {
		decorated = inject instanceof TestSubInjectImpl;
		inject.doSomething();
	}

	public static boolean decoratedSubInject() {
		return decorated;
	}
	
	public static void reset() {
		decorated = true;
	}
}
