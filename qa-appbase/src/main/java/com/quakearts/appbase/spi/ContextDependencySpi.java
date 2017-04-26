package com.quakearts.appbase.spi;

import javax.enterprise.inject.spi.BeanManager;

public interface ContextDependencySpi {
	void initiateContextDependency();
	void shutDownContextDependency();
	Object getMainSingleton(Class<?> mainSingletonClass);
	BeanManager getBeanManager();
}
