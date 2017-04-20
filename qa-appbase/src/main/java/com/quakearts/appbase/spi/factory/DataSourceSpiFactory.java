package com.quakearts.appbase.spi.factory;

import com.quakearts.appbase.Main;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.spi.DataSourceProviderSpi;

public class DataSourceSpiFactory {

	private DataSourceSpiFactory() {
	}

	private static final DataSourceSpiFactory instance = new DataSourceSpiFactory();
	
	public static DataSourceSpiFactory getInstance() {
		return instance;
	}
	
	private DataSourceProviderSpi dataSourceProviderSpi;
	
	public DataSourceProviderSpi getDataSourceProviderSpi() {
		return dataSourceProviderSpi;
	}
	
	public DataSourceProviderSpi createDataSourceProviderSpi(String dataSourceProviderSpiClassName){
		try {
			Class<?> javaTmSpiClass = Class.forName(dataSourceProviderSpiClassName);
			Main.log.info("DataSourceProviderSpi class: "+dataSourceProviderSpiClassName+" loaded");
			dataSourceProviderSpi = (DataSourceProviderSpi) javaTmSpiClass.newInstance();
			return dataSourceProviderSpi;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException| ClassCastException e) {
			throw new ConfigurationException("Unable to instantiate class "+dataSourceProviderSpiClassName, e);
		}
	}
}
