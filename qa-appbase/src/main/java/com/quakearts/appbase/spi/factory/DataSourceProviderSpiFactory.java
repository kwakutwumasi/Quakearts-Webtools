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
import com.quakearts.appbase.spi.DataSourceProviderSpi;

public class DataSourceProviderSpiFactory {

	private DataSourceProviderSpiFactory() {
	}

	private static final DataSourceProviderSpiFactory instance = new DataSourceProviderSpiFactory();
	
	public static DataSourceProviderSpiFactory getInstance() {
		return instance;
	}
	
	private DataSourceProviderSpi dataSourceProviderSpi;
	
	public DataSourceProviderSpi getDataSourceProviderSpi() {
		return dataSourceProviderSpi;
	}
	
	public DataSourceProviderSpi createDataSourceProviderSpi(String dataSourceProviderSpiClassName){
		if(dataSourceProviderSpi == null)
			try {
				Class<?> javaTmSpiClass = Class.forName(dataSourceProviderSpiClassName);
				Main.log.info("DataSourceProviderSpi class: {} loaded", dataSourceProviderSpiClassName);
				dataSourceProviderSpi = (DataSourceProviderSpi) javaTmSpiClass.newInstance();
				return dataSourceProviderSpi;
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException| ClassCastException e) {
				throw new ConfigurationException("Unable to instantiate class "+dataSourceProviderSpiClassName, e);
			}
		else
			throw new ConfigurationException("Cannot create two instances of "+DataSourceProviderSpi.class.getName());
	}
}
