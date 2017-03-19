package com.quakearts.webapp.orm.stringconcat;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Transient;

public class OrmStringConcat {	
	private Map<String, ConcatenatorEntry> fieldMap;
	private Class<?> beanClass;
	
	final static Logger log = Logger.getLogger(OrmStringConcat.class.getName());
	
	public OrmStringConcat(Class<?> beanClass) {
		fieldMap = new HashMap<>();
		
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
		
			for(PropertyDescriptor descriptor:beanInfo.getPropertyDescriptors()){
				if(descriptor.getPropertyType() == String.class
						&& descriptor.getWriteMethod()!=null
						&& descriptor.getReadMethod()!=null){

					Field field = null;
					try {
						field = beanClass.getDeclaredField(descriptor.getName());
					} catch (NoSuchFieldException | SecurityException e) {
						try {
							field = getField(descriptor.getName(), beanClass.getSuperclass());
						} catch (NoSuchFieldException e1) {
						}
					}
					
					if(field!=null){
						if(field.getAnnotation(Transient.class)!=null)
							continue;
					} else {
						if(descriptor.getReadMethod().getAnnotation(Transient.class)!=null)
							continue;
						
						if(descriptor.getWriteMethod().getAnnotation(Transient.class)!=null)
							continue;
					}
					
					fieldMap.put(descriptor.getName(),
							new ConcatenatorEntry(beanClass.getName(), 
									descriptor.getName(), 
									getMaxLength(descriptor, field, beanClass),
									getMethodHandle(descriptor.getWriteMethod()), 
									getMethodHandle(descriptor.getReadMethod())));
				}
			}
		} catch (IntrospectionException | IllegalAccessException e) {
			log.warning("Unable to introspect " + beanClass.getName() 
					+ " for use with " 
					+ getClass().getName() + ": "
					+ e.getMessage());
		}
		
		this.beanClass = beanClass;
	}
	
	String concatenate(String value, int maxLength){
		return value.length()>maxLength? value.substring(0,maxLength):value;
	}
		
	int getMaxLength(PropertyDescriptor descriptor, Field field, Class<?> beanClass){
		Column column = null; 
		if(field!=null)
			column = field.getAnnotation(Column.class);
		
		if(column != null){
			return column.length();
		} else {
			column = descriptor.getWriteMethod().getAnnotation(Column.class);
			if(column==null){
				column = descriptor.getReadMethod().getAnnotation(Column.class);
			}
			
			if(column!=null)
				return column.length();
			else
				return 255;
		}
	}
	
	Field getField(String fieldName, Class<?> superClass) throws NoSuchFieldException {
		if(superClass == Object.class)
			throw new NoSuchFieldException("Field '"+fieldName+"' could not be found.");
		
		try {
			return superClass.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			return getField(fieldName, superClass.getSuperclass());
		}
	}
	
	MethodHandle getMethodHandle(Method method) throws IllegalAccessException{
		MethodHandle handle = MethodHandles.lookup().unreflect(method);
		return new ConstantCallSite(handle).dynamicInvoker();
	}
	
	public void trimStrings(Object object) {
		if(object.getClass().isAssignableFrom(beanClass)){
			if(fieldMap.size()<=0)
				return;
			
			for(ConcatenatorEntry entry: fieldMap.values()){
				Object value;
				try {
					value = entry.getMethodHandle.invoke(object);
				} catch (Throwable e) {
					throw new RuntimeException("Unable to get string to concat", e);
				}
				if(value==null)
					continue;
				
				Object trimedValue = concatenate(value.toString(), entry.fieldLength);
				if(value != trimedValue){
					log.warning("Value "+value
							+" is too long for field "
							+entry.fieldName
							+". Max field size is "
							+entry.fieldLength
							+" for objects of type "
							+entry.className
							+". The value has been trimmed");

					try {
						entry.writeMethodHandle.invoke(object, trimedValue);
					} catch (Throwable e) {
						throw new RuntimeException("Unable to concat string", e);
					}
				}
			}
		} else {
			throw new IllegalArgumentException("Object is not a subtype of "+beanClass.getName());
		}
	}
}

class ConcatenatorEntry {
	int fieldLength;
	MethodHandle writeMethodHandle;
	MethodHandle getMethodHandle;
	String fieldName;
	String className;
	
	public ConcatenatorEntry(String className, String fieldName, int fieldLength, MethodHandle writeMethodHandle, MethodHandle getMethodHandle) {
		this.fieldLength = fieldLength;
		this.writeMethodHandle = writeMethodHandle;
		this.getMethodHandle = getMethodHandle;
		this.fieldName = fieldName;
		this.className = className;
	}
}
