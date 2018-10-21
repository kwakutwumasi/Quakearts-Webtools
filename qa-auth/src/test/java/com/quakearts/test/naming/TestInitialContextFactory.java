package com.quakearts.test.naming;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.ObjectFactory;

import org.apache.naming.NamingContext;

public class TestInitialContextFactory implements InitialContextFactory, ObjectFactory {

	public static NamingContext context;
	@SuppressWarnings("unchecked")
	@Override
	public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
		if(context==null)
			context = new NamingContext((Hashtable<String, Object>)environment,"");
		
		return context;
	}

	@Override
	public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
			throws Exception {
		return getInitialContext(environment);
	}

}
