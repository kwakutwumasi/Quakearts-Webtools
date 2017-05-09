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
		try {
			weld.shutdown();
		} catch (Throwable e) {
			// Suppress error
		}
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
