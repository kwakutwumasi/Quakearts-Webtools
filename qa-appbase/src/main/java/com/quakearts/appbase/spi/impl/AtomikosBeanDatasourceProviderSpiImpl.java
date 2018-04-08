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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.quakearts.appbase.Main;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.internal.properties.AppBasePropertiesLoader;
import com.quakearts.appbase.internal.properties.ConfigurationPropertyMap;
import com.quakearts.appbase.spi.DataSourceProviderSpi;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;

public class AtomikosBeanDatasourceProviderSpiImpl implements DataSourceProviderSpi {

	private Map<String, DataSource> datasources = new ConcurrentHashMap<>();
	private List<AtomikosDataSourceBean> dataSourceBeans = new ArrayList<>();
	
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
		
		List<ConfigurationPropertyMap> loadedConfigurationPropertyFiles = appBasePropertiesLoader
				.getAllConfigurationProperties("atomikos"+File.separator+"datasources", ".ds.json", "QA AppBase Atomikos datasource definition");
		
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("default.ds.json");
		if(in!=null)
			try(InputStream readin = in;) {
				ConfigurationPropertyMap defaultConfigurationPropertyMap
					= appBasePropertiesLoader.loadParametersFromReader(".classpath", new InputStreamReader(readin));
				loadedConfigurationPropertyFiles.add(defaultConfigurationPropertyMap);
			} catch (IOException e) {
				Main.log.error("Unable to load default.ds.json", e);
			}
		for(ConfigurationPropertyMap loadedConfigurationPropertyFile:loadedConfigurationPropertyFiles){
			getDataSource(loadedConfigurationPropertyFile);
		}
	}	
	
	@Override
	public DataSource getDataSource(ConfigurationPropertyMap configurationParameters) {
		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setXaDataSourceClassName((String) configurationParameters.get(DATASOURCECLASS));
		atomikosDataSourceBean.setXaProperties(getProperties(configurationParameters));		
		setOtherParameters(atomikosDataSourceBean, configurationParameters);
		
		if(configurationParameters.containsKey(NAME)){
			atomikosDataSourceBean.setUniqueResourceName(configurationParameters.getString(NAME));
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
			ConfigurationPropertyMap configurationParameters) {
		if (configurationParameters.containsKey("borrowConnectionTimeout"))
			atomikosDataSourceBean.setBorrowConnectionTimeout(configurationParameters.getInt("borrowConnectionTimeout"));

		if (configurationParameters.containsKey("loginTimeout"))
			try {
				atomikosDataSourceBean.setLoginTimeout(configurationParameters.getInt("loginTimeout"));
			} catch (SQLException e) {
				throw new ConfigurationException(e.getMessage(), e);
			}

		if (configurationParameters.containsKey("maintenanceInterval"))
			atomikosDataSourceBean.setMaintenanceInterval(configurationParameters.getInt("maintenanceInterval"));

		if (configurationParameters.containsKey("maxIdleTime"))
			atomikosDataSourceBean.setMaxIdleTime(configurationParameters.getInt("maxIdleTime"));
		
		if (configurationParameters.containsKey("maxLifetime"))
			atomikosDataSourceBean.setMaxLifetime(configurationParameters.getInt("maxLifetime"));
		
		if (configurationParameters.containsKey("maxPoolSize"))
			atomikosDataSourceBean.setMaxPoolSize(configurationParameters.getInt("maxPoolSize"));
		
		if (configurationParameters.containsKey("minPoolSize"))
			atomikosDataSourceBean.setMinPoolSize(configurationParameters.getInt("minPoolSize"));
		
		if (configurationParameters.containsKey("poolSize"))
			atomikosDataSourceBean.setPoolSize(configurationParameters.getInt("poolSize"));
		
		if (configurationParameters.containsKey("reapTimeout"))
			atomikosDataSourceBean.setReapTimeout(configurationParameters.getInt("reapTimeout"));
		
		if (configurationParameters.containsKey("testQuery"))
			atomikosDataSourceBean.setTestQuery(configurationParameters.getString("testQuery"));
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
		if(dataSourceBeans.isEmpty())
			return;
		
		dataSourceBeans.stream().forEach((dataSourceBean)-> dataSourceBean.close());
	}
}
