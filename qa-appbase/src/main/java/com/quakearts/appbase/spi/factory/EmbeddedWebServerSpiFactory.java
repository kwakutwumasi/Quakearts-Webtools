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
import com.quakearts.appbase.spi.EmbeddedWebServerSpi;

public class EmbeddedWebServerSpiFactory {

	private EmbeddedWebServerSpiFactory() {
	}
	
	private static EmbeddedWebServerSpiFactory instance = new EmbeddedWebServerSpiFactory();
	
	public static EmbeddedWebServerSpiFactory getInstance() {
		return instance;
	}
	
	private EmbeddedWebServerSpi webServerSpi;
	
	public EmbeddedWebServerSpi createEmbeddedWebServerSpi(String embeddedSpiClassname) {
		try {
			Class<?> javaTmSpiClass = Class.forName(embeddedSpiClassname);
			Main.log.info("EmbeddedWebServerSpi class: "+embeddedSpiClassname+" loaded");
			webServerSpi = (EmbeddedWebServerSpi) javaTmSpiClass.newInstance();
			return webServerSpi;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException| ClassCastException e) {
			throw new ConfigurationException("Unable to instantiate class "+embeddedSpiClassname, e);
		}
	}

}
