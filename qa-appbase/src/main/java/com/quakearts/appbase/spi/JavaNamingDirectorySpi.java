package com.quakearts.appbase.spi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public interface JavaNamingDirectorySpi {
	InitialContext getInitialContext();
	Context createContext(String name) throws NamingException;
	void initiateJNDIServices();
	void shutdownJNDIService();
}
