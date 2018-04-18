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
package com.quakearts.appbase.spi.impl.tomcat;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.inject.spi.InjectionTargetFactory;
import javax.enterprise.inject.spi.Unmanaged;
import javax.enterprise.inject.spi.Unmanaged.UnmanagedInstance;
import javax.naming.NamingException;
import org.apache.tomcat.InstanceManager;

import com.quakearts.appbase.Main;

public class AppBaseCDIInstanceManager implements InstanceManager {
	BeanManager manager;
	private final Map<Object, UnmanagedInstance<?>> unmanagedCache = new ConcurrentHashMap<>();
	private final Map<Object, Boolean> otherObjectsCache = new ConcurrentHashMap<Object, Boolean>();
	
	public AppBaseCDIInstanceManager(BeanManager manager) {
		this.manager = manager;
	}

	@Override
	public Object newInstance(Class<?> clazz)
			throws IllegalAccessException, InvocationTargetException, NamingException, InstantiationException {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		UnmanagedInstance<?> instance = new Unmanaged(clazz).newInstance();
		Object o = instance.produce()
				.inject()
				.postConstruct()
				.get();
		
		unmanagedCache.put(o, instance);
		return o;
	}

	@Override
	public Object newInstance(String className) throws IllegalAccessException, InvocationTargetException,
			NamingException, InstantiationException, ClassNotFoundException {
		try {
			return newInstance(Class.forName(className));			
		} catch (ClassNotFoundException e) {
			throw e;
		}
	}

	@Override
	public Object newInstance(String fqcn, ClassLoader classLoader) throws IllegalAccessException,
			InvocationTargetException, NamingException, InstantiationException, ClassNotFoundException {
		if(classLoader!=Main.class.getClassLoader()) {
			Class<?> clazz = classLoader.loadClass(fqcn);
			Object o = clazz.newInstance();
			otherObjectsCache.put(o, Boolean.TRUE);
			return o;
		} else {
			return newInstance(fqcn);
		}
	}

	@Override
	public void newInstance(Object o) throws IllegalAccessException, InvocationTargetException, NamingException {
		inject(o);
	}

	private void inject(Object o) {
		AnnotatedType<?> type = manager.createAnnotatedType(o.getClass());
		InjectionTargetFactory<?> factory = manager.getInjectionTargetFactory(type);
		@SuppressWarnings("unchecked")
		InjectionTarget<Object> target = (InjectionTarget<Object>) factory.createInjectionTarget(null);
		target.inject(o, null);		
	}
	
	@Override
	public void destroyInstance(Object o) throws IllegalAccessException, InvocationTargetException {
		if(!unmanagedCache.containsKey(o))
			return;
		
		UnmanagedInstance<?> unmanaged = unmanagedCache.get(o);
		if(unmanaged!=null) {
			unmanaged.preDestroy();
			unmanaged.dispose();
			unmanagedCache.remove(o);
		}
	}

}
