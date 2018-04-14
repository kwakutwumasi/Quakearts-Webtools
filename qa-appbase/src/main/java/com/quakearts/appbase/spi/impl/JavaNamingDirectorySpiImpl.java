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
package com.quakearts.appbase.spi.impl;

import javax.enterprise.inject.Vetoed;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.naming.NamingContext;

import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.spi.JavaNamingDirectorySpi;

@Vetoed
public class JavaNamingDirectorySpiImpl implements JavaNamingDirectorySpi {

	@Override
	public void initiateJNDIServices() {
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.quakearts.appbase.spi.impl.naming.AppBaseInitialContextFactory");
		System.setProperty(Context.URL_PKG_PREFIXES, "com.quakearts.appbase.spi.impl.naming");		
	}

	@Override
	public void shutdownJNDIService() {
	}

	private static InitialContext initial;
	
	@Override
	public InitialContext getInitialContext() {
		if(initial == null)
			try {
				initial = new InitialContext();
			} catch (NamingException e) {
				//Should not happen
				throw new ConfigurationException(e.getMessage(), e);
			}
		return initial;
	}
	
	@Override
	public Context createContext(String name) throws NamingException {
		getInitialContext().bind(name, new NamingContext(null, name));
		return (Context) getInitialContext().lookup(name);
	}
}
