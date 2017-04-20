package com.quakearts.appbase.spi.factory;

import com.quakearts.appbase.Main;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.spi.ContextDependencySpi;

public class ContextDependencySpiFactory {

	private ContextDependencySpiFactory() {
	}
	
	private static ContextDependencySpiFactory instance = new ContextDependencySpiFactory();
	
	public static ContextDependencySpiFactory getInstance() {
		return instance;
	}
	
	private ContextDependencySpi dependencySpi;
	
	public ContextDependencySpi getDependencySpi() {
		return dependencySpi;
	}
	
	public ContextDependencySpi createContextDependencySpi(String cdiSpiClassname) {
		try {
			Class<?> cdiSpiClass = Class.forName(cdiSpiClassname);
			Main.log.info("ContextDependencySpi class: "+cdiSpiClassname+" loaded");
			dependencySpi = (ContextDependencySpi) cdiSpiClass.newInstance();
			return dependencySpi;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException| ClassCastException e) {
			throw new ConfigurationException("Unable to instantiate class "+cdiSpiClassname, e);
		}
	}
}
