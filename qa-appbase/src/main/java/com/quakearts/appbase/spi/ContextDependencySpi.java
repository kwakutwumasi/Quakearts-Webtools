package com.quakearts.appbase.spi;

import javax.enterprise.inject.spi.BeanManager;

public interface ContextDependencySpi {
	void initiateContextDependency();
	void shutDownContextDependency();
	void runMainSingleton(String className);
	BeanManager getBeanManager();
}
