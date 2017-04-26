package com.quakearts.appbase.spi.impl;

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
	public Object getMainSingleton(Class<?> mainClass) {
		return container.select(mainClass).get();
	}
	
	@Override
	public BeanManager getBeanManager() {
		if(container!=null)
			return container.getBeanManager();
		
		return null;
	}
	
}
