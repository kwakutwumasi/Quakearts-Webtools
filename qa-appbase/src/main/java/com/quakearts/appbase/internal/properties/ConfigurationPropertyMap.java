/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
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

import javax.enterprise.inject.Vetoed;

import com.quakearts.appbase.exception.ConfigurationException;

/**
 * An implementation of {@link java.util.Map Map} with special get methods for
 * obtaining primitive property values
 * 
 * @author kwakutwumasi-afriyie
 *
 */
@Vetoed
public class ConfigurationPropertyMap extends HashMap<String, Serializable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3193170233757422518L;

	/**
	 * Return a long value with the property name
	 * 
	 * @param property
	 *            the name of the property
	 * @return a long value
	 * @throws ConfigurationException
	 *             if the returned property is not a valid long value
	 */
	public long getLong(String property) {
		return getLongObject(property);
	}

	private Long getLongObject(String property) {
		Serializable object = get(property);
		if (object == null) {
			return Long.valueOf(0l);
		} else if (Long.class == object.getClass()) {
			return (Long) object;
		} else {
			throw new ConfigurationException(property + " is not a valid long integer");
		}
	}

	/**
	 * Return an int value with the property name
	 * 
	 * @param property
	 *            the name of the property
	 * @return a int value
	 * @throws ConfigurationException
	 *             if the returned property is not a valid int value
	 */
	public int getInt(String property) {
		return getInteger(property);
	}

	private Integer getInteger(String property) {
		Serializable object = get(property);
		if (object == null) {
			return Integer.valueOf(0);
		} else if (Integer.class == object.getClass()) {
			return (Integer) object;
		} else {
			throw new ConfigurationException(property + " is not a valid integer");
		}
	}

	/**
	 * Return a boolean value with the property name
	 * 
	 * @param property
	 *            the name of the property
	 * @return a boolean value
	 * @throws ConfigurationException
	 *             if the returned property is not a valid boolean value
	 */
	public boolean getBoolean(String property) {
		return getBooleanObject(property);
	}

	private Boolean getBooleanObject(String property) {
		Serializable object = get(property);
		if (object == null) {
			return Boolean.FALSE;
		} else if (Boolean.class == object.getClass()) {
			return (Boolean) object;
		} else {
			throw new ConfigurationException(property + " is not a valid boolean");
		}
	}

	/**
	 * Return a double value with the property name
	 * 
	 * @param property
	 *            the name of the property
	 * @return a double value
	 * @throws ConfigurationException
	 *             if the returned property is not a valid double value
	 */
	public double getDouble(String property) {
		return getDoubleObject(property);
	}

	private Double getDoubleObject(String property) {
		Serializable object = get(property);
		if (object == null) {
			return Double.valueOf(0);
		} else if (Double.class == object.getClass()) {
			return (Double) object;
		} else {
			throw new ConfigurationException(property + " is not a valid double precision number");
		}
	}

	/**
	 * Return a float value with the property name
	 * 
	 * @param property
	 *            the name of the property
	 * @return a double value
	 * @throws ConfigurationException
	 *             if the returned property is not a valid double value
	 */
	public float getFloat(String property) {
		return getFloatObject(property);
	}

	private Float getFloatObject(String property) {
		Serializable object = get(property);
		if (object == null) {
			return Float.valueOf(0);
		} else if (Float.class == object.getClass()) {
			return (Float) object;
		} else {
			throw new ConfigurationException(property + " is not a valid floating point number");
		}
	}

	/**
	 * Return a sub ConfigurationPropertyMap
	 * 
	 * @param property
	 * @throws ConfigurationException
	 *             if the returned property is not a valid ConfigurationPropertyMap
	 */
	public ConfigurationPropertyMap getSubConfigurationPropertyMap(String property) {
		return get(property, ConfigurationPropertyMap.class);
	}

	/**
	 * Return a value cast to the specified class with the property name. This
	 * method attempts to cast the returned value to the class specified. If the
	 * returned object cannot be cast, an attempt is made to find a constructor that
	 * takes a single parameter of type {@linkplain String} and calls it, passing
	 * the result of calling the object's {@link Object#toString() toString()}
	 * method.
	 * 
	 * @param property
	 *            the name of the property
	 * @param clazz
	 *            the class the object should be cast to
	 * @return a value cast to the class specified
	 * @throws ConfigurationException
	 *             if the returned property cannot be cast to the specified class
	 * @throws IllegalArgumentException
	 *             if the class is a primitive class (int,long, etc)
	 */
	public <T> T get(String property, Class<T> clazz) {
		Serializable object = get(property);
		if (object == null)
			return null;

		if (clazz.isPrimitive()) {
			throw new IllegalArgumentException(
					"Cannot cast wrapper classes from primitive class. Use get methods for primitives.");
		}

		if (clazz.isAssignableFrom(object.getClass())) {
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

			throw new ConfigurationException(
					property + " of type " + object.getClass().getName() + " cannot be cast to " + clazz.getName(),
					cause);
		}
	}

	/**
	 * Populate the given Java Bean with values from the map. This method uses
	 * introspection to view all the writable properties of the Java Bean and tries
	 * to find the corresponding property in the map. If such a value is found, then
	 * an attempt to cast the value to the expected type is made using the
	 * {@linkplain #get(String, Class)} method of this class.
	 * 
	 * @param target
	 *            the object to populate
	 * @param prefix
	 *            the prefix for properties of the target object
	 * @throws IntrospectionException
	 *             if the target object cannot be introspected
	 * @throws ConfigurationException
	 *             if a property cannot be written to due to other Exceptions
	 */
	public void populateBean(Object target, String prefix) throws IntrospectionException {
		BeanInfo info = Introspector.getBeanInfo(target.getClass());

		for (PropertyDescriptor propertyDescriptor : info.getPropertyDescriptors()) {
			setProperty(target, prefix, propertyDescriptor);
		}
	}

	private void setProperty(Object target, String prefix, PropertyDescriptor propertyDescriptor) {
		String propertyName = (prefix != null ? prefix + "." : "") + propertyDescriptor.getName();
		try {
			if (containsKey(propertyName) && propertyDescriptor.getWriteMethod() != null) {
				Method setMethod = propertyDescriptor.getWriteMethod();
				Object argument = null;
				Class<?> type = propertyDescriptor.getPropertyType();
				if (type.isPrimitive()) {
					argument = extractPrimitive(propertyName, type.getName());
				} else {
					argument = get(propertyName, type);
				}

				setMethod.invoke(target, argument);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ConfigurationException(
					propertyName + " cannot be written to " + target.getClass().getName() + ": " + e.getMessage(), e);
		}
	}

	private Object extractPrimitive(String propertyName, String type) {
		if(type.equals("byte"))
			return (byte) getInteger(propertyName).intValue();
		if(type.equals("short"))
			return (short) getInteger(propertyName).intValue();
		if(type.equals("int"))
			return getInteger(propertyName);
		if(type.equals("char")) {
			Object argument = getString(propertyName);
			if (argument != null && !argument.toString().trim().isEmpty())
				argument = argument.toString().charAt(0);
			else
				argument = '\0';
			return argument;
		}
		if(type.equals("long"))
			return getLongObject(propertyName);
		if(type.equals("float"))
			return getFloatObject(propertyName);
		if(type.equals("double"))
			return getDoubleObject(propertyName);

		return getBooleanObject(propertyName);
	}

	/**
	 * Return a {@linkplain String} value with the property name
	 * 
	 * @param property
	 *            the name of the property
	 * @return a {@linkplain String} value
	 * @throws ConfigurationException
	 *             if the returned property is not a valid {@linkplain String} value
	 */
	public String getString(String name) {
		return get(name, String.class);
	}

}
