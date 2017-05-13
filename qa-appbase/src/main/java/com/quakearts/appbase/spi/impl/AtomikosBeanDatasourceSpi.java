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
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.quakearts.appbase.internal.json.Json;
import com.quakearts.appbase.internal.json.JsonObject;
import com.quakearts.appbase.internal.json.JsonObject.Member;
import com.quakearts.appbase.internal.json.JsonValue;
import com.quakearts.appbase.internal.json.ParseException;
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
		
		List<File> datasourceFiles = listDatasourceFiles();
		
		for(File file:datasourceFiles){
			Map<String, Serializable> parameters = loadParametersFromFile(file);
			getDataSource(parameters);
		}
	}
	
	private List<File> listDatasourceFiles() {
		List<File> datasourceFiles = new ArrayList<>();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if(classLoader instanceof URLClassLoader){
			URL[] urls = ((URLClassLoader) classLoader).getURLs();
			for(URL url:urls){
				try {
					File directoryFile = new File(url.toURI());
					if(directoryFile.exists() && directoryFile.isDirectory()){
						for(File file:directoryFile.listFiles()){
							if(file.exists() 
									&& file.isFile() 
									&& file.getName().endsWith(".ds.json"))
								datasourceFiles.add(file);
						}
					}
				} catch (URISyntaxException e) {
					//Skip
				}
			}
		}
		
		File datasourceLocation = new File("."+File.separator+"atomikos"+File.separator+"datasources");
		if(datasourceLocation.exists() && datasourceLocation.isDirectory()){
			for(File file:datasourceLocation.listFiles()){
				if(file.exists() 
						&& file.isFile() 
						&& file.getName().endsWith(".ds.json"))
					datasourceFiles.add(file);
			}
		} else if(!datasourceLocation.isFile()){
			datasourceLocation.mkdirs();
			Main.log.info("Created "+datasourceLocation.getAbsolutePath()
				+" to store QA AppBase Atomikos datasource definion files (*.ds.json)");
		} else {
			Main.log.warn("File "+datasourceLocation.getAbsolutePath()+" is not a directory. "
					+ "This location must be a folder to hold datasource files");
		}
		
		return datasourceFiles;
	}

	private Map<String, Serializable> loadParametersFromFile(File file){
		Map<String, Serializable> map = new HashMap<>();
		try(FileReader reader = new FileReader(file)) {
			JsonValue datasourceValue = Json.parse(reader);
			
			if(!datasourceValue.isObject()){
				throw new ConfigurationException("Invalid datasource file. Must be a single json object with name:value pairs.");
			}
			
			datasourceValue.asObject().forEach((c)->{
				if(c.getValue().isBoolean()){
					map.put(c.getName(), c.getValue().asBoolean());
				} else if(c.getValue().isNumber()){
					if(c.getValue().toString().contains("."))
						map.put(c.getName(), c.getValue().asDouble());
					else
						map.put(c.getName(), new Double(c.getValue().asDouble()).intValue());
				} else if(c.getValue().isString()){
					map.put(c.getName(), c.getValue().asString());
				} else if(c.getValue().isObject()){
					JsonObject object = c.getValue().asObject();
					if(object.isEmpty())
						throw new ConfigurationException("Invalid datasource parameter "+c.getName()+". Must not be empty.");
						
					Member objectMember = object.iterator().next();
					Serializable value;
					switch (objectMember.getName()) {
					case "double":
						value = objectMember.getValue().asDouble();
						break;
					case "float":
						value = objectMember.getValue().asFloat();
						break;
					case "chars":
						value = objectMember.getValue().asString().toCharArray();
						break;
					case "char":
						value = objectMember.getValue().asString().toCharArray()[0];
						break;
					case "long":
						value = objectMember.getValue().asLong();
						break;
					default:
						throw new ConfigurationException("Invalid datasource parameter "+c.getName()+". Must not be empty. Conversion not understood: "+objectMember.getName());
					}
					map.put(c.getName(), value);
				}
			});
		} catch (IOException | ParseException e) {
			throw new ConfigurationException("Exception of type " 
					+ e.getClass().getName() 
					+ " was thrown. Message is " 
					+ e.getMessage()
					+ ". Exception occured whiles reading datasource file "
					+ file, e);
		}
		
		return map;
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

	@SuppressWarnings("unchecked")
	private <T> T get(String name, Class<T> clazz, Map<String, Serializable> configurationParameters){
		return (T) configurationParameters.get(name);
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