package com.quakearts.appbase.internal.properties;

import java.io.Serializable;
import java.util.HashMap;

import com.quakearts.appbase.exception.ConfigurationException;

public class ConfigurationPropertyMap extends HashMap<String, Serializable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3193170233757422518L;

	private boolean canCast(String property, Class<?> castClass, Serializable object) {
		return containsKey(property)? castClass.isAssignableFrom(object.getClass()):false;
	}
	
	public long getLong(String property) throws ConfigurationException {
		Serializable object = get(property);
		if (object == null) {
			return 0l;
		} else if(canCast(property, Long.class, object)) {
			return (Long) object;
		} else 
			throw new ConfigurationException(property+" is not a valid long integer");
	}
	
	public int getInt(String property) throws ConfigurationException {
		Serializable object = get(property);
		if (object == null) {
			return 0;
		} else if(canCast(property, Integer.class, object)) {
			return (Integer) object;
		} else 
			throw new ConfigurationException(property+" is not a valid integer");
	}
	
	public boolean getBoolean(String property) throws ConfigurationException {
		Serializable object = get(property);
		if (object == null) {
			return false;
		} else if(canCast(property, Boolean.class, object)) {
			return (Boolean) object;
		} else 
			throw new ConfigurationException(property+" is not a valid boolean");
	}

	public double getDouble(String property) throws ConfigurationException {
		Serializable object = get(property);
		if (object == null) {
			return 0;
		} else if(canCast(property, Double.class, object)) {
			return (Double) object;
		} else 
			throw new ConfigurationException(property+" is not a valid double precision number");
	}
	
	public float getFloat(String property) throws ConfigurationException {
		Serializable object = get(property);
		if (object == null) {
			return 0;
		} else if(canCast(property,Float.class, object)) {
			return (Float) object;
		} else 
			throw new ConfigurationException(property+" is not a valid floating point number");
	}
	
	public String getString(String property) throws ConfigurationException {
		Serializable object = get(property);
		if (object == null) {
			return null;
		} else if(canCast(property, String.class, object)) {
			return (String) object;
		} else
			throw new ConfigurationException(property+" is not a valid string");
	}
	
	public byte[] getBytes(String property) throws ConfigurationException {
		Serializable object = get(property);
		if (object == null) {
			return null;
		} else if(canCast(property, byte[].class, object)) {
			return (byte[]) object;
		} else
			throw new ConfigurationException(property+" is not a valid binary string");
	}
	
	public <T> T get(String property, Class<T> clazz){
		Serializable object = get(property);
		if(object == null || !clazz.isAssignableFrom(object.getClass()))
			return null;
		
		return clazz.cast(object);
	}	

}
