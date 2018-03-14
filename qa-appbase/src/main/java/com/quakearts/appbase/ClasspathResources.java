package com.quakearts.appbase;

import java.net.URL;

import com.quakearts.appbase.exception.ConfigurationException;

public interface ClasspathResources {
	URL getLibraryPath(String libraryName) throws ConfigurationException;
	void addLibraryPath(URL libraryPathUrl) throws ConfigurationException;
	void loadClassPath() throws ConfigurationException;
}
