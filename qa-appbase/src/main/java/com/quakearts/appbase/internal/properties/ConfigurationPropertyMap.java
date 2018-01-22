package com.quakearts.appbase.internal.properties;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.quakearts.appbase.exception.ConfigurationException;

public class ConfigurationPropertyMap extends HashMap<String, Serializable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3193170233757422518L;	
	public long getLong(String property) throws ConfigurationException {
		Serializable object = get(property);
		if (object == null) {
			return 0l;
		} else if(Long.class == object.getClass()) {
			return (Long) object;
		} else 
			throw new ConfigurationException(property+" is not a valid long integer");
	}
	
	public int getInt(String property) throws ConfigurationException {
		Serializable object = get(property);
		if (object == null) {
			return 0;
		} else if(Integer.class == object.getClass()) {
			return (Integer) object;
		} else 
			throw new ConfigurationException(property+" is not a valid integer");
	}
	
	public boolean getBoolean(String property) throws ConfigurationException {
		Serializable object = get(property);
		if (object == null) {
			return false;
		} else if(Boolean.class == object.getClass()) {
			return (Boolean) object;
		} else 
			throw new ConfigurationException(property+" is not a valid boolean");
	}

	public double getDouble(String property) throws ConfigurationException {
		Serializable object = get(property);
		if (object == null) {
			return 0;
		} else if(Double.class == object.getClass()) {
			return (Double) object;
		} else 
			throw new ConfigurationException(property+" is not a valid double precision number");
	}
	
	public float getFloat(String property) throws ConfigurationException {
		Serializable object = get(property);
		if (object == null) {
			return 0;
		} else if(Float.class == object.getClass()) {
			return (Float) object;
		} else 
			throw new ConfigurationException(property+" is not a valid floating point number");
	}
	
	public <T> T get(String property, Class<T> clazz) throws ConfigurationException{
		Serializable object = get(property);
		if(object == null)
			return null;

		if(clazz.isPrimitive()) {
			throw new IllegalArgumentException("Cannot cast wrapper classes from primitive class. Use get methods for primitives.");
		}
		
		if(clazz.isAssignableFrom(object.getClass())) {
			return clazz.cast(object);
		} else {
			Exception cause = null;
			try {
				Constructor<T> constructor = clazz.getConstructor(String.class);
				return constructor.newInstance(object.toString());
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				cause = e;
			}
			
			throw new ConfigurationException(property+" of type "+object.getClass().getName()
					+" cannot be cast to "+clazz.getName(), cause);
		}		
	}

	/**
	 * @param target the object to populate
	 * @param prefix the prefix for properties of the target object
	 * @throws IntrospectionException
	 */
	public void populateBean(Object target, String prefix) throws IntrospectionException{
		BeanInfo info = Introspector.getBeanInfo(target.getClass());
		
		for(PropertyDescriptor propertyDescriptor: info.getPropertyDescriptors()){
			String propertyName = (prefix!=null?prefix+".":"")+propertyDescriptor.getName();
			try {
				if(containsKey(propertyName) &&
						propertyDescriptor.getWriteMethod()!=null){
					Method setMethod = propertyDescriptor.getWriteMethod();
					Object argument = null;
					Class<?> type = propertyDescriptor.getPropertyType();
					if(type.isPrimitive()) {
						switch (type.getName()) {
						case "byte":
							argument = (byte) getInt(propertyName);
							break;
						case "short":
							argument = (short) getInt(propertyName);
							break;
						case "int":
							argument = getInt(propertyName);
							break;
						case "char":
							argument = getString(propertyName);
							if(argument!=null && !argument.toString().trim().isEmpty())
								argument = argument.toString().charAt(0);
							else
								argument = '\0';
							break;
						case "long":
							argument = getLong(propertyName);
							break;
						case "float":
							argument = getFloat(propertyName);
							break;
						case "double":
							argument = getDouble(propertyName);
							break;
						case "boolean":
							argument = getBoolean(propertyName);
							break;
						}
					} else {
						argument = get(propertyName, type);
					}
					
					setMethod.invoke(target, argument);
				}
			} catch (IllegalAccessException | IllegalArgumentException |InvocationTargetException e) {
				throw new ConfigurationException(propertyName+" cannot be written to "+target.getClass().getName()+": "+e.getMessage());
			}
		}
	}

	public String getString(String name) {
		return get(name, String.class);
	}	

}
