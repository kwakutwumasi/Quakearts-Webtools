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

import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.internal.properties.ConfigurationPropertyMap;

/**The SPI for JCA implementations. Implementers should use this method to bootstrap the JCA implementation
 * @author kwakutwumasi-afriyie
 *
 */
public interface DataSourceProviderSpi {
	public static final String DATASOURCECLASS = "datasource.class", 
			NAME = "datasource.name";
	/**Start the JCA implementation
	 * @throws ConfigurationException if there is an error initializing the instance
	 */
	void initiateDataSourceSpi();
	/**Configure and return a datasource using the given {@linkplain ConfigurationPropertyMap} object
	 * @param configurationParameters the {@linkplain ConfigurationPropertyMap} object containing initialization parameters
	 * @return a {@linkplain DataSource} object
	 */
	DataSource getDataSource(ConfigurationPropertyMap configurationParameters);
	/**Find and return a {@linkplain DataSource} with the given name
	 * @param name the name of the {@linkplain DataSource}
	 * @return the {@linkplain DataSource}, if any
	 */
	DataSource getDataSource(String name);
	/**Shutdown the JCA implementation, cleaning up and releasing held resources.
	 * A successful call to {@link #initiateDataSourceSpi()} should be possible after this method exits
	 * @throws ConfigurationException if there is an error shutting down the instance
	 */
	void shutDownDataSourceProvider();
}
