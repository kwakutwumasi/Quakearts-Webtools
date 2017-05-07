package com.quakearts.appbase.spi.support.naming;


import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.ObjectFactory;

import org.apache.naming.NamingContext;

public class spoolerURLContextFactory implements InitialContextFactory, ObjectFactory {

	private static NamingContext context;
	private static final String TRANSCONTEXT = "spooler:";
	
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
