package com.quakearts.workflowapp.jbpm.util;

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
	
	public static URL getRootDir() throws Exception{
		return Thread.currentThread().getContextClassLoader().getResource(".");
	}

	public static InitialContext getInitialContext() {
		return icx;
	}
	
	public static UserTransaction getTransaction() throws NamingException{
		return (UserTransaction) icx.lookup("java:comp/UserTransaction");
	}
	
	public static String prettyFormatJSON(String jsonString){
    	int level = 0;
    	boolean newline=false;
    	StringBuilder builder = new StringBuilder();
    	char p='\0';
    	for(char c:jsonString.toCharArray()){
    		if(newline && (c!='}' &&  c!=']' && c!=',' && (c!='{' || p!='['))){			
    			builder.append('\n');
    			for(int i=0;i<level;i++)
    				builder.append("  ");
    			newline = false;
    		}
    		switch (c) {
    		case '{':
    		case '[':
    			builder.append(c);
    			level++;
    			newline = true;
    			break;
    		case ',':
    			builder.append(c);
    			newline = true;
    			break;
    		case '}':
    		case ']':
    			level--;
    			if(c!=']' || p!='}'){
    				builder.append('\n');
    				for(int i=0;i<level;i++)
    					builder.append("  ");
    			}
    			builder.append(c);
    			newline = true;
    			break;
    		default:
    			builder.append(c);
    			break;
    		}
    		p=c;
    	}
    	
    	return builder.toString();
    }
    
	@SafeVarargs
	public static <T> T[] ar(T...ts){
		return ts;
	}
	
	public static <T> T[] ar(){
		return null;
	}

	public static <T,V> ConstMap<T,V> map() {
		return new ConstMap<T, V>();
	}
	
	@SuppressWarnings("serial")
	public static class ConstMap<T,V> extends HashMap<T,V> {
		
		public ConstMap() {
			super(50, 1.0F);
		}
		
		public ConstMap<T, V> add(T key, V value) {
			put(key, value);
			return this;
		}
		
		public Map<T,V> fix() {
			return Collections.unmodifiableMap(this);
		}
	}
    
}
