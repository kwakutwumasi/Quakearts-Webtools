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
package com.quakearts.appbase.spi.impl.naming.java;


import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.ObjectFactory;

import org.apache.naming.NamingContext;

public class javaURLContextFactory implements InitialContextFactory, ObjectFactory {

	private static NamingContext context;
	private static final String TRANSCONTEXT = "java:";
	
	public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
			throws Exception {
		return getInitialContext(environment);
	}

	public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
		return getContext(environment);
	}
	
	@SuppressWarnings({ "unchecked"})
	private static NamingContext getContext(Hashtable<?, ?> environment) throws NamingException {
		if(context==null){
			context = new NamingContext((Hashtable<String, Object>)environment, TRANSCONTEXT);
			context.bind(TRANSCONTEXT, context);
		}
		
		return context;
	}

}
