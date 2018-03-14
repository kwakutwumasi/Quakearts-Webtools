package com.quakearts.appbase.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.quakearts.appbase.ClasspathResources;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.impl.ClasspathResourcesImpl;

public class TestClasspathResourcesSpiImpl {

	@Test
	public void testGetFromClassLoader() throws Exception {
		try {
			ClasspathResources spi = new ClasspathResourcesImpl();
			spi.loadClassPath();
			spi.getLibraryPath("tomcat-embed-el-8.5.9.jar");
		} catch (ConfigurationException e) {
			fail("Configuration exception: "+e.getMessage());
		}
	}

	@Test
	public void testGetFromSystemProperties() throws Exception {
		try {
			ClasspathResourcesImpl spi = new ClasspathResourcesImpl();
			spi.loadFromSystemProperty();
			spi.getLibraryPath("tomcat-embed-el-8.5.9.jar");
		} catch (ConfigurationException e) {
			fail("Configuration exception: "+e.getMessage());
		}
	}

}
