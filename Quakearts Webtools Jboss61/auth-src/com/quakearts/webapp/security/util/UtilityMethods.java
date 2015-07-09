package com.quakearts.webapp.security.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

public class UtilityMethods {

	private static final InitialContext icx = startInitialContext();

	private static InitialContext startInitialContext(){
		InitialContext icx;
		try {
			icx = new InitialContext();
			return icx;
		} catch (NamingException e) {
			return null;
		}
    }
	
	private UtilityMethods() {
	}
	
	public static InputStream getResource(String name){
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
	}
	
	public static OutputStream getOutputstream(String name) throws Exception{
		OutputStream out = null;
		URL url = Thread.currentThread().getContextClassLoader().getResource(name);
		if(url!=null){
			out = new FileOutputStream(URLDecoder.decode(url.getFile(),"UTF-8"));
		}
		return out;
	}

	public static URL getRootDir() throws Exception{
		return Thread.currentThread().getContextClassLoader().getResource(".");
	}

	public static InitialContext getInitialContext() {
		return icx;
	}
	
	public static UserTransaction getTransaction() throws NamingException{
		return (UserTransaction) icx.lookup("java:comp/UserTransaction");
	}
}
