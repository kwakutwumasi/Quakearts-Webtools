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

import javax.enterprise.inject.spi.CDI;

public class TestStatic {	
	private static TestStatic instance;
	
	TestStatic() {
	}
	
	public static TestStatic getInstance() {
		if(instance==null)
			instance = CDI.current().select(TestStatic.class).get();
		
		return instance;
	}
	
	@TestingInterceptor
	public void intercepted(){
	}
}
