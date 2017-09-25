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
package com.quakearts.appbase.spi;

import javax.sql.DataSource;

import com.quakearts.appbase.internal.properties.ConfigurationPropertyMap;

public interface DataSourceProviderSpi {
	public static final String DATASOURCECLASS = "datasource.class", 
			NAME = "datasource.name";

	void initiateDataSourceSpi();

	DataSource getDataSource(ConfigurationPropertyMap configurationParameters);

	DataSource getDataSource(String name);

	void shutDownDataSourceProvider();
}
