package com.quakearts.webapp.beansupport;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.quakearts.webapp.beansupport.emtpyhandler.BeanEmptyHandler;
import com.quakearts.webapp.beansupport.emtpyhandlerimpl.CollectionEmptyHandler;
import com.quakearts.webapp.beansupport.emtpyhandlerimpl.MapEmptyHandler;
import com.quakearts.webapp.beansupport.emtpyhandlerimpl.StringEmptyHandler;
import com.quakearts.webapp.beansupport.exception.BeanUpdaterException;

public class BeanUpdater<T> {
	private Map<String, UpdaterHandles> cache;
	private static final Map<Class<?>, BeanEmptyHandler<?>> emptyHandlers = new ConcurrentHashMap<>();
	
	private boolean ignoreNullAndEmpty, treatZeroAsEmpty;
	
	static {
		registerEmptyHandlers(Collection.class, new CollectionEmptyHandler());
		registerEmptyHandlers(String.class, new StringEmptyHandler());
		registerEmptyHandlers(Map.class, new MapEmptyHandler());
	}
	
	public BeanUpdater(Class<T> clazz) throws IntrospectionException, IllegalAccessException {
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		cache = new HashMap<>();
		for(PropertyDescriptor descriptor:beanInfo.getPropertyDescriptors()){
			BeanEmptyHandler<?> handler = emptyHandlers.get(descriptor.getPropertyType());
			if(handler==null){
				for(Entry<Class<?>, BeanEmptyHandler<?>> entry:emptyHandlers.entrySet()){
					if(entry.getKey().isAssignableFrom(descriptor.getPropertyType())){
						handler = entry.getValue();
						break;
					}
				}
			}
				
			cache.put(descriptor.getName(), new UpdaterHandles(getMethodHandle(descriptor.getReadMethod()), 
					getMethodHandle(descriptor.getWriteMethod()), handler));			
		}
		
		cache = Collections.unmodifiableMap(cache);
	}
	
	public BeanUpdater<T> ignoreNullAndEmpty(){
		ignoreNullAndEmpty = true;
		return this;
	}
	
	public BeanUpdater<T> treatZeroAsEmpty() {
		treatZeroAsEmpty = true;
		return this;
	}
	
	public static void registerEmptyHandlers(Class<?> clazz, BeanEmptyHandler<?> handler){
		emptyHandlers.put(clazz, handler);
	}
		
	@SuppressWarnings("unchecked")
	public boolean update(T source, T destination) throws BeanUpdaterException{
		boolean canUpdate = false;
		
		for(UpdaterHandles handles:cache.values()){
			try {
				Object value = handles.get.invoke(source);
				Object oldValue = handles.get.invoke(destination);
				
				if(oldValue!=null && oldValue.equals(value))
					continue;
				
				if(ignoreNullAndEmpty){
					if(value==null)
						continue;
					
					if(value!=null 
							&& handles.handler != null 
							&& handles.handler.isEmpty(value))
						continue;
				}
				
				if(treatZeroAsEmpty 
						&& value instanceof Number 
						&&((Number)value).doubleValue() == 0)
						continue;
				
				handles.set.invoke(destination, value);
				if(!canUpdate)
					canUpdate = true;
			} catch (Throwable e) {
				throw new BeanUpdaterException(e);
			}
		}
		
		return canUpdate;
	}
	
	private MethodHandle getMethodHandle(Method method) throws IllegalAccessException{
		if(method==null)
			return null;
		
		MethodHandle handle = MethodHandles.lookup().unreflect(method);
		return new ConstantCallSite(handle).dynamicInvoker();
	}
	
	private class UpdaterHandles {
		MethodHandle get;
		MethodHandle set;
		@SuppressWarnings("rawtypes")
		BeanEmptyHandler handler;
		
		public UpdaterHandles(MethodHandle get, MethodHandle set, BeanEmptyHandler<?> handler) {
			this.get = get;
			this.set = set;
			this.handler = handler;
		}
			
	}
}
