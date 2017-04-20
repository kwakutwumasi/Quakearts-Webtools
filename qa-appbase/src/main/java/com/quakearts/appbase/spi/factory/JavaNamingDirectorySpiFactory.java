package com.quakearts.appbase.spi.factory;

import com.quakearts.appbase.Main;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.spi.JavaNamingDirectorySpi;

public class JavaNamingDirectorySpiFactory {

	private JavaNamingDirectorySpiFactory() {
	}

	private static final JavaNamingDirectorySpiFactory instance = new JavaNamingDirectorySpiFactory();
	
	public static JavaNamingDirectorySpiFactory getInstance() {
		return instance;
	}
	
	private JavaNamingDirectorySpi javaNamingDirectorySpi;
	
	public JavaNamingDirectorySpi getJavaNamingDirectorySpi() {
		return javaNamingDirectorySpi;
	}
	
	public JavaNamingDirectorySpi createJavaNamingDirectorySpi(String javaNamingDirectorySpiClassName){
		try {
			Class<?> javaTmSpiClass = Class.forName(javaNamingDirectorySpiClassName);
			Main.log.info("EmbeddedWebServerSpi class: "+javaNamingDirectorySpiClassName+" loaded");
			javaNamingDirectorySpi = (JavaNamingDirectorySpi) javaTmSpiClass.newInstance();
			return javaNamingDirectorySpi;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException| ClassCastException e) {
			throw new ConfigurationException("Unable to instantiate class "+javaNamingDirectorySpiClassName, e);
		}
	}
}
