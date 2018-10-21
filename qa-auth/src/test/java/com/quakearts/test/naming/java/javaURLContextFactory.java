package com.quakearts.test.naming.java;

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

	@Override
	public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
			throws Exception {
		return getInitialContext(environment);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
		if(context == null) {
			context = new NamingContext((Hashtable<String, Object>) environment, TRANSCONTEXT);
			context.bind(TRANSCONTEXT, context);
		}
		return context;
	}

}
