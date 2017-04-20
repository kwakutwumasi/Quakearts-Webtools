package com.quakearts.appbase.spi.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.spi.DataSourceProviderSpi;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;

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
	}
	
	@Override
	public DataSource getDataSource(Map<String, Serializable> configurationParameters) {
		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setXaDataSourceClassName((String) configurationParameters.get(DATASOURCECLASS));
		atomikosDataSourceBean.setXaProperties(getProperties(configurationParameters));
		
		if(configurationParameters.containsKey(NAME)){
			atomikosDataSourceBean.setUniqueResourceName(get(NAME,String.class,configurationParameters));
			datasources.put(atomikosDataSourceBean.getUniqueResourceName(), atomikosDataSourceBean);
			try {
				JavaNamingDirectorySpiFactory.getInstance().getJavaNamingDirectorySpi().getInitialContext().bind("java:/jdbc/" + atomikosDataSourceBean.getUniqueResourceName(), atomikosDataSourceBean);
			} catch (NamingException e) {
				throw new ConfigurationException("Unable to bind name 'java:/jdbc/" + atomikosDataSourceBean.getUniqueResourceName()+"' to context");
			}
		} else {
			throw new ConfigurationException("Missing unique datasource name");
		}
		
		setOtherParameters(atomikosDataSourceBean, configurationParameters);
		
		return atomikosDataSourceBean;
	}

	private void setOtherParameters(AtomikosDataSourceBean atomikosDataSourceBean,
			Map<String, Serializable> configurationParameters) {
		atomikosDataSourceBean.setBorrowConnectionTimeout(get("borrowConnectionTimeout", Integer.class, configurationParameters));
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

	@SuppressWarnings("unchecked")
	private <T> T get(String name, Class<T> clazz, Map<String, Serializable> configurationParameters){
		return (T) configurationParameters.get(name);
	}
	
	@Override
	public DataSource getDataSource(String name) {
		return datasources.get(name);
	}

	Properties getProperties(Map<String, Serializable> configurationParameters){
		Properties properties = new Properties();
		properties.putAll(configurationParameters);
		
		return properties;
	}
	
	@Override
	public void shutDownDataSourceProvider() {
	}
}
