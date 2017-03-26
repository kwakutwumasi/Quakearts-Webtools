package com.quakearts.webapp.facelets.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

public class UtilityMethods {

	private static final InitialContext icx = startInitialContext();
	private static final String patternCacheKey = "com.quakearts.bootstrap";

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
	
	public static void delete(File file, StringBuilder summary){
		if(file == null)
			return;
		if(file.isDirectory()){
			for(File fileEntry:file.listFiles()){
				delete(fileEntry,summary);
			}
			summary.append((file.delete()?"Deleted ":"Could not delete ") +file.getName()+" directory. <br />");
		}else{
			summary.append((file.delete()?"Deleted ":"Could not delete ") +file.getName()+" . <br />");
		}
	}
	
    public static ArrayList<HashMap<String,Serializable>> saveResults(ResultSet rs) throws SQLException{
        ArrayList<HashMap<String,Serializable>> results = new ArrayList<HashMap<String,Serializable>>();
        ResultSetMetaData rsmd = rs.getMetaData();
        do{
            HashMap<String,Serializable> rsrows = new HashMap<String,Serializable>();
            for(int i=1;i<rsmd.getColumnCount();i++){
            	Serializable serializable = extract(rs,rsmd.getColumnType(i),i);
            	String key;
            	key = rsmd.getColumnName(i);
            	if(key==null||key.trim().length()==0)
            		key = ""+i;
            	
                if(!rs.wasNull()){
                	rsrows.put(key, serializable==null?"":serializable);
                }else{
                	rsrows.put(key,"");
                }
            }
            results.add(rsrows);
        }while(rs.next());
        return results;
    }
    
    public static Serializable extract(ResultSet rs, int type,int column){
        try {
    	switch(type){
	        case Types.INTEGER:
	            return Long.valueOf((rs.getInt(column)));
	        case Types.BIGINT:
	            return rs.getBigDecimal(column);
	        case Types.DECIMAL:
	            return Double.valueOf(rs.getDouble(column));
	        case Types.BIT:
	            return Byte.valueOf(rs.getByte((column)));
	        case Types.DOUBLE:
	            return Double.valueOf(rs.getDouble(column));
	        case Types.FLOAT:
	        	return Double.valueOf(rs.getDouble(column));
	        case Types.NUMERIC:
	        	return Double.valueOf(rs.getDouble(column));
	        case Types.REAL:
	        	return Double.valueOf(rs.getDouble(column));
	        case Types.SMALLINT:
	        	return Integer.valueOf((rs.getInt(column)));
	        case Types.TINYINT:
	        	return Integer.valueOf((rs.getInt(column)));
	        case Types.BOOLEAN:
	        	return Boolean.valueOf(rs.getBoolean(column));
	        case Types.TIME:
	        	return rs.getTime(column);
	        case Types.TIMESTAMP:
	        	return rs.getTimestamp(column);
	        case Types.DATE:
	        	return rs.getDate(column);
	        default:
	        	return rs.getString(column);
    	}
        } catch(Exception e){
        	return null;
        }
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
	
    public static boolean componentIsDisabled(UIComponent component) {
        return (Boolean.valueOf(String.valueOf(component.getAttributes().get("disabled"))));
    }

    public synchronized static String[] split(Map<String, Object> appMap, String toSplit, String regex) {
        Map<String, Pattern> patternCache = getPatternCache(appMap);
        Pattern pattern = patternCache.get(regex);
        if (pattern == null) {
            pattern = Pattern.compile(regex);
            patternCache.put(regex, pattern);
        }
        return  pattern.split(toSplit, 0);
        
    }
    
    @SuppressWarnings("unchecked")
	private static Map<String,Pattern> getPatternCache(Map<String, Object> appMap) {
        Map<String,Pattern> result = 
                (Map<String,Pattern>) appMap.get(patternCacheKey);
        if (null == result) {
            result = new LRUMap<String,Pattern>(15);
            appMap.put(patternCacheKey, result);
        }
        
        return result;
    }
    
	@SuppressWarnings("rawtypes")
	public static Converter getConverterForClass(Class converterClass,
			FacesContext context) {
		if (converterClass == null) {
			return null;
		}
		try {
			Application application = context.getApplication();
			return (application.createConverter(converterClass));
		} catch (Exception e) {
			return (null);
		}
	}
	
    public static boolean componentIsDisabledOrReadonly(UIComponent component) {
        return Boolean.valueOf(String.valueOf(component.getAttributes().get("disabled"))) || Boolean.valueOf(String.valueOf(component.getAttributes().get("readonly")));
    }
    
}
