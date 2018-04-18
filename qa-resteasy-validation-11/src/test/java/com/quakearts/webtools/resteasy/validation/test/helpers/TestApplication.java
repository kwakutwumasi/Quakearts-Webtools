/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.webtools.resteasy.validation.test.helpers;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("rest")
public class TestApplication extends Application {
	
	Set<Class<?>> singletons = new HashSet<>();
	
	@Override
	public Set<Class<?>> getClasses() {
		singletons.add(TestResource.class);
		singletons.add(TestProvider.class);
		singletons.add(TestConstraintExceptionMapper.class);
		return singletons;
	}	
}
