/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.test;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@TestingInterceptor @Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class TestInterceptor {
	
	@AroundInvoke
	public Object testInvoke(InvocationContext context) throws Exception{
		System.out.println("Intercepting...");
		Object object = context.proceed();
		System.out.println("Intercepted!");
		
		return object;
	}
}
