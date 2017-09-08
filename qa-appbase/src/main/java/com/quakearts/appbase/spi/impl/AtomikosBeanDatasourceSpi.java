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

import java.io.File;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.internal.properties.AppBasePropertiesLoader;
import com.quakearts.appbase.spi.DataSourceProviderSpi;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import static com.quakearts.appbase.internal.properties.AppBasePropertiesLoader.get;

public class AtomikosBeanDatasourceSpi implements DataSourceProviderSpi {

	private static Map<String, DataSource> datasources = new ConcurrentHashMap<>();
	
	@Override
	public void initiateDataSourceSpi() {
		try {
			JavaNamingDirectorySpiFactory.getInstance()
				.getJavaNamingDirectorySpi()
				.createContext("java:/jdbc");
		} catch (NamingException e) {
			new ConfigurationException(e.getMessage(),e);
		}

		AppBasePropertiesLoader appBasePropertiesLoader = new AppBasePropertiesLoader();
		
		List<Map<String, Serializable>> loadedConfigurationPropertyFiles = appBasePropertiesLoader
				.getAllConfigurationProperties("."+File.separator+"atomikos"+File.separator+"datasources", ".ds.json", "QA AppBase Atomikos datasource definition");
		
		for(Map<String, Serializable> loadedConfigurationPropertyFile:loadedConfigurationPropertyFiles){
			getDataSource(loadedConfigurationPropertyFile);
		}
	}	
	
	@Override
	public DataSource getDataSource(Map<String, Serializable> configurationParameters) {
		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setXaDataSourceClassName((String) configurationParameters.get(DATASOURCECLASS));
		atomikosDataSourceBean.setXaProperties(getProperties(configurationParameters));		
		setOtherParameters(atomikosDataSourceBean, configurationParameters);
		
		if(configurationParameters.containsKey(NAME)){
			atomikosDataSourceBean.setUniqueResourceName(get(NAME, String.class, configurationParameters));
			datasources.put(atomikosDataSourceBean.getUniqueResourceName(), atomikosDataSourceBean);
			try {
				JavaNamingDirectorySpiFactory.getInstance().getJavaNamingDirectorySpi().getInitialContext().bind("java:/jdbc/" + atomikosDataSourceBean.getUniqueResourceName(), atomikosDataSourceBean);
			} catch (NamingException e) {
				throw new ConfigurationException("Unable to bind name 'java:/jdbc/" + atomikosDataSourceBean.getUniqueResourceName()+"' to context");
			}
		} else {
			throw new ConfigurationException("Missing unique datasource name");
		}
		
		return atomikosDataSourceBean;
	}

	private void setOtherParameters(AtomikosDataSourceBean atomikosDataSourceBean,
			Map<String, Serializable> configurationParameters) {
		if (configurationParameters.containsKey("borrowConnectionTimeout"))
			atomikosDataSourceBean.setBorrowConnectionTimeout(get("borrowConnectionTimeout", Integer.class, configurationParameters));

		if (configurationParameters.containsKey("loginTimeout"))
			try {
				atomikosDataSourceBean.setLoginTimeout(get("loginTimeout", Integer.class, configurationParameters));
			} catch (SQLException e) {
				throw new ConfigurationException(e.getMessage(), e);
			}

		if (configurationParameters.containsKey("maintenanceInterval"))
			atomikosDataSourceBean.setMaintenanceInterval(get("maintenanceInterval", Integer.class, configurationParameters));

		if (configurationParameters.containsKey("maxIdleTime"))
			atomikosDataSourceBean.setMaxIdleTime(get("maxIdleTime", Integer.class, configurationParameters));
		
		if (configurationParameters.containsKey("maxLifetime"))
			atomikosDataSourceBean.setMaxLifetime(get("maxLifetime", Integer.class, configurationParameters));
		
		if (configurationParameters.containsKey("maxPoolSize"))
			atomikosDataSourceBean.setMaxPoolSize(get("maxPoolSize", Integer.class, configurationParameters));
		
		if (configurationParameters.containsKey("minPoolSize"))
			atomikosDataSourceBean.setMinPoolSize(get("minPoolSize", Integer.class, configurationParameters));
		
		if (configurationParameters.containsKey("poolSize"))
			atomikosDataSourceBean.setPoolSize(get("poolSize", Integer.class, configurationParameters));
		
		if (configurationParameters.containsKey("reapTimeout"))
			atomikosDataSourceBean.setReapTimeout(get("reapTimeout", Integer.class, configurationParameters));
		
		if (configurationParameters.containsKey("testQuery"))
			atomikosDataSourceBean.setTestQuery(get("testQuery", String.class, configurationParameters));
	}

	@Override
	public DataSource getDataSource(String name) {
		return datasources.get(name);
	}

	private Properties getProperties(Map<String, Serializable> configurationParameters){
		Properties properties = new Properties();
		for(Entry<String, Serializable> entry: configurationParameters.entrySet()){
			if(entry.getKey().startsWith("xa."))
				properties.put(entry.getKey().substring(3), entry.getValue());
		}
		
		return properties;
	}
	
	@Override
	public void shutDownDataSourceProvider() {
	}
}
