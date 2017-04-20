package com.quakearts.appbase.spi;

import java.io.Serializable;
import java.util.Map;

import javax.sql.DataSource;

public interface DataSourceProviderSpi {
	public static final String DATASOURCECLASS="com.quakearts.appbase.datasource.CLASS",
			DRIVERCLASS="com.quakearts.appbase.datasource.DRIVER", DATASOURCEURL="com.quakearts.appbase.datasource.URL", 
			USERNAME="com.quakearts.appbase.datasource.USERNAME", PASSWORD="com.quakearts.appbase.datasource.PASSWORD",
					NAME="com.quakearts.appbase.datasource.NAME";	
	void initiateDataSourceSpi();
	DataSource getDataSource(Map<String, Serializable> configurationParameters);
	DataSource getDataSource(String name);
	void shutDownDataSourceProvider();
}
