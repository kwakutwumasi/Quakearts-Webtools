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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.inject.Vetoed;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.quakearts.appbase.Main;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.internal.properties.AppBasePropertiesLoader;
import com.quakearts.appbase.internal.properties.ConfigurationPropertyMap;
import com.quakearts.appbase.internal.properties.impl.AppBasePropertiesLoaderImpl;
import com.quakearts.appbase.spi.DataSourceProviderSpi;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;

/**An implementation of the {@linkplain DataSourceProviderSpi} that starts up {@linkplain AtomikosDataSourceBean}
 * instances with that wrap the underlying implementation of {@linkplain DataSource}.
 * The implementation looks for files with the extension <code>ds.json</code> in the <code>atomikos/datasources</code> folder.
 * For users that need only one datasource there are several options:
 * <br />* A JSON file named defualt.ds.json can be placed at the root of the classpath. 
 * <br />* Users who prefer environment variables can set variables named after the properties and prefixed by 'ds.'
 * (case sensitive environments) or 'DS_' (case insensitive environments). For case insensitive environments, 
 * CamelCase properties should be converted to underscore variants. Ex a property named camelCase should be stored as CAMEL_CASE. (See 
 * {@linkplain AppBasePropertiesLoaderImpl#loadParametersFromEnvironment(String)}).
 * <br />* For users who wish to use a single configuration file the properties can be stored in the 'app.config.json' at the root of the classpath under 
 * the JSON property "datasource".
 * <br /><br />
 * AtomikosBeanDatasourceProviderSpi uses the following properties:
 * <br /><br />
 * <code>datasource.class</code>: a JSON string value. The FQDN of the {@linkplain DataSource} implementation to use
 * <br />
 * <code>datasource.name</code>: a JSON string value. The JNDI name of the DataSource object. It should not be fully qualified.
 * <br />
 * <code>xa.xxx</code>: property xxx will be set on the DataSource object.
 * <br />
 * <code>borrowConnectionTimeout</code>: a JSON integer value. Sets the maximum amount of time in seconds 
 * the pool will block waiting for a connection to become available in the pool when it is empty.
 *  Zero or negative means no waiting at all. Defaults to 30 seconds. Optional.
 * <br />
 * <code>loginTimeout</code>: a JSON integer value. Sets the maximum time in seconds that this data source will wait while attempting 
 * to connect to a database. A value of zero specifies that the timeout is the 
 * default system timeout if there is one; otherwise, it specifies that there 
 * is no timeout. When a DataSource object is created, the login timeout is initially zero.
 * <br />
 * <code>maintenanceInterval</code>: a JSON integer value. Sets the maintenance interval for the pool maintenance thread. The interval is in seconds. 
 * If not set or not positive then the pool's default (60 secs) will be used. Optional.
 * <br />
 * <code>maxIdleTime</code>: a JSON integer value. Sets the maximum amount of seconds that unused excess connections should stay in the pool. Optional. 
 * Note: excess connections are connections that are created above the minPoolSize limit.
 * The value is the preferred idle time for unused excess connections. Note that this value is only an indication; 
 * the pool will check regularly as indicated by the maintenanceInteval property. The default is 60 seconds.
 * <br />
 * <code>maxLifetime</code>: a JSON integer value. Sets the maximum amount of seconds that a connection is kept in the pool 
 * before it is destroyed automatically. Optional, defaults to 0 (no limit).
 * <br />
 * <code>maxPoolSize</code>: a JSON integer value. Sets the maximum pool size. The amount of pooled connections won't go above this value. Optional, defaults to 1.
 * <br />
 * <code>minPoolSize</code>: a JSON integer value. Sets the minimum pool size. The amount of pooled connections won't go below that value. 
 * The pool will open this amount of connections during initialization. Optional, defaults to 1.
 * <br />
 * <code>reapTimeout</code>: a JSON integer value. Sets the amount of time (in seconds) that the connection pool will allow a connection to be in use, before claiming it back.
 * Zero means unlimited. Note that this value is only an indication; the pool will check regularly as indicated by the maintenanceInteval property. 
 * Default is 0 (no timeout).
 * <br />
 * <code>testQuery</code>: a JSON string value. Sets the SQL query or statement used to validate a connection before returning it. Optional.
 * <br />
 * <code>poolSize</code>: a JSON integer value. Sets both the minimal and maximal pool size. Required if the maxPoolSize is not set. 
 * Overrides any minPoolSize or maxPoolSize settings you might have configured before.
 * <br />
 * @author kwakutwumasi-afriyie
 *
 */
@Vetoed
public class AtomikosBeanDatasourceProviderSpiImpl implements DataSourceProviderSpi {

	private static final String JAVA_JDBC = "java:/jdbc";
	private static final String JAVA_JDBC_PREFIX = "java:/jdbc/";
	private Map<String, AtomikosDataSourceBean> datasources = new ConcurrentHashMap<>();
	private String resourceLocation = "atomikos";
	
	public AtomikosBeanDatasourceProviderSpiImpl() {
	}
	
	public AtomikosBeanDatasourceProviderSpiImpl(String resourceLocation) {
		this.resourceLocation = resourceLocation;
	}

	@Override
	public void initiateDataSourceSpi() {
		try {
			JavaNamingDirectorySpiFactory.getInstance()
				.getJavaNamingDirectorySpi()
				.createContext(JAVA_JDBC);
		} catch (NamingException e) {
			throw new ConfigurationException(e.getMessage(),e);
		}

		AppBasePropertiesLoader appBasePropertiesLoaderImpl = Main.getAppBasePropertiesLoader();
		
		List<ConfigurationPropertyMap> loadedConfigurationPropertyFiles = appBasePropertiesLoaderImpl
				.getAllConfigurationProperties(resourceLocation+File.separator+"datasources", ".ds.json", "QA AppBase Atomikos datasource definition");
		
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("default.ds.json");
		if(in!=null) {
			try(InputStream readin = in;) {
				ConfigurationPropertyMap defaultConfigurationPropertyMap
					= appBasePropertiesLoaderImpl.loadParametersFromReader(".classpath", new InputStreamReader(readin));
				loadedConfigurationPropertyFiles.add(defaultConfigurationPropertyMap);
			} catch (IOException e) {
				Main.log.error("Unable to load default.ds.json", e);
			}
		} else {		
			ConfigurationPropertyMap environmentVariableMap = appBasePropertiesLoaderImpl.loadParametersFromEnvironment("ds");
			if(!environmentVariableMap.isEmpty()) {
				loadedConfigurationPropertyFiles.add(environmentVariableMap);
			} else {
				ConfigurationPropertyMap systemWideConfigurationPropertyMap = Main.getInstance().getAppConfiguration().getSubConfigurationPropertyMap("datasource");
				if(systemWideConfigurationPropertyMap!=null)
					loadedConfigurationPropertyFiles.add(systemWideConfigurationPropertyMap);
			}
		}
		
		for(ConfigurationPropertyMap loadedConfigurationPropertyFile:loadedConfigurationPropertyFiles){
			getDataSource(loadedConfigurationPropertyFile);
		}
	}	
	
	@Override
	public DataSource getDataSource(ConfigurationPropertyMap configurationParameters) {
		AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
		atomikosDataSourceBean.setXaDataSourceClassName((String) configurationParameters
				.get(PropertyNames.DATASOURCECLASS.getPropertyName()));
		atomikosDataSourceBean.setXaProperties(getProperties(configurationParameters));		
		setOtherParameters(atomikosDataSourceBean, configurationParameters);
		
		if(configurationParameters.containsKey(PropertyNames.NAME.getPropertyName())){
			atomikosDataSourceBean.setUniqueResourceName(configurationParameters
					.getString(PropertyNames.NAME.getPropertyName()));
			datasources.put(atomikosDataSourceBean.getUniqueResourceName(), atomikosDataSourceBean);
			try {
				JavaNamingDirectorySpiFactory.getInstance().getJavaNamingDirectorySpi().getInitialContext().bind(JAVA_JDBC_PREFIX + atomikosDataSourceBean.getUniqueResourceName(), atomikosDataSourceBean);
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
		
		if (configurationParameters.containsKey("concurrentConnectionValidation"))
			atomikosDataSourceBean.setConcurrentConnectionValidation(configurationParameters.getBoolean("concurrentConnectionValidation"));
		
		if (configurationParameters.containsKey("reapTimeout"))
			atomikosDataSourceBean.setReapTimeout(configurationParameters.getInt("reapTimeout"));
		
		if (configurationParameters.containsKey("testQuery"))
			atomikosDataSourceBean.setTestQuery(configurationParameters.getString("testQuery"));

		if (configurationParameters.containsKey("localTransactionMode"))
			atomikosDataSourceBean.setLocalTransactionMode(configurationParameters.getBoolean("localTransactionMode"));
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
		if(datasources.isEmpty())
			return;
		final InitialContext initialContext = JavaNamingDirectorySpiFactory
				.getInstance()
				.getJavaNamingDirectorySpi()
				.getInitialContext(); 
		datasources.entrySet().stream().forEach(entry->{
			try {
				initialContext.unbind(JAVA_JDBC_PREFIX + entry.getValue().getUniqueResourceName());
			} catch (NamingException e) {
				//Do nothing
			}
		});
		try {
			initialContext.unbind(JAVA_JDBC);
		} catch (NamingException e) {
			//Do nothing
		}
		datasources.clear();
	}
}
