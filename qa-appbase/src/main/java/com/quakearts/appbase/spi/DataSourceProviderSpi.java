package com.quakearts.appbase.spi;

import java.io.Serializable;
import java.util.Map;

import javax.sql.DataSource;

public interface DataSourceProviderSpi {
	public static final String DATASOURCECLASS = "datasource.class", 
			NAME = "datasource.name";

	void initiateDataSourceSpi();

	DataSource getDataSource(Map<String, Serializable> configurationParameters);

	DataSource getDataSource(String name);

	void shutDownDataSourceProvider();
}
