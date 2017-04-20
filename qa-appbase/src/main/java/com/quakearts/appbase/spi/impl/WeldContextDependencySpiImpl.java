package com.quakearts.appbase.spi.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.enterprise.inject.spi.BeanManager;

import org.jboss.weld.bootstrap.api.helpers.RegistrySingletonProvider;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.spi.ContextDependencySpi;

public class WeldContextDependencySpiImpl implements ContextDependencySpi {

	private Weld weld;
	private WeldContainer container;

	@Override
	public void initiateContextDependency() {
        weld = new Weld().containerId(RegistrySingletonProvider.STATIC_INSTANCE);
        container = weld.initialize();
	}

	@Override
	public void shutDownContextDependency() {
		if (weld == null) {
			throw new ConfigurationException("Call to shutdown Weld without existing instance");
		}
		weld.shutdown();
	}
	
	@Override
	public void runMainSingleton(String className) {
		try {
			Class<?> mainClass = Class.forName(className);
			Object mainInstance = container.select(mainClass).get();
			Method initMethod = mainClass.getMethod("init");
			initMethod.invoke(mainInstance);
		} catch (ClassNotFoundException | NoSuchMethodException 
				| SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			throw new ConfigurationException("Cannot load mainclass "+className, e);
		}
	}
	
	@Override
	public BeanManager getBeanManager() {
		if(container!=null)
			return container.getBeanManager();
		
		return null;
	}
	
}
