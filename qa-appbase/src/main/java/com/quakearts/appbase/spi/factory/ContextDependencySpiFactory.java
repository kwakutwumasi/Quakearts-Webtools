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
	
	public ContextDependencySpi getContextDependencySpi() {
		return dependencySpi;
	}
	
	public ContextDependencySpi createContextDependencySpi(String cdiSpiClassname) {
		if(dependencySpi == null)
			try {
				Class<?> cdiSpiClass = Class.forName(cdiSpiClassname);
				Main.log.info("ContextDependencySpi class: {} loaded", cdiSpiClassname);
				dependencySpi = (ContextDependencySpi) cdiSpiClass.newInstance();
				return dependencySpi;
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException| ClassCastException e) {
				throw new ConfigurationException("Unable to instantiate class "+cdiSpiClassname, e);
			}
		else
			throw new ConfigurationException("Cannot create two instances of "+ContextDependencySpi.class.getName());
	}
}
