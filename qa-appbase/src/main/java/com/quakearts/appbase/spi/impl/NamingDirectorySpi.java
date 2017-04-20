package com.quakearts.appbase.spi.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.naming.NamingContext;

import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.spi.JavaNamingDirectorySpi;

public class NamingDirectorySpi implements JavaNamingDirectorySpi {

	@Override
	public void initiateJNDIServices() {
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.quakearts.appbase.spi.support.naming.AppBaseInitialContextFactory");
		System.setProperty(Context.URL_PKG_PREFIXES, "com.quakearts.appbase.spi.support.naming");		
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
