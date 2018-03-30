package com.quakearts.appbase.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import java.net.URL;
import java.util.Enumeration;
import javax.servlet.ServletContainerInitializer;
import org.apache.catalina.WebResourceRoot;
import org.junit.Test;

import com.quakearts.appbase.impl.ClasspathResourcesImpl;
import com.quakearts.appbase.spi.impl.tomcat.AppBaseVirtualDirectoryResourceSet;
import com.quakearts.appbase.spi.impl.tomcat.AppBaseWebappClassLoader;
import com.quakearts.appbase.test.helpers.TestContext;

public class TestAppBaseWebappClassLoader {

	@Test(expected=ClassNotFoundException.class)
	public void runTestWithoutStarting() throws Exception {
		ClasspathResourcesImpl impl = new ClasspathResourcesImpl();
		impl.loadClassPath();
		TestContext context = new TestContext();
		WebResourceRoot root = context.getResources();
		AppBaseVirtualDirectoryResourceSet resourceSet = new AppBaseVirtualDirectoryResourceSet(root, "/WEB-INF/lib", "/repo", "/");
		resourceSet.addUrl("tomcat-embed-el-8.5.9.jar", impl.getLibraryPath("tomcat-embed-el-8.5.9.jar"));
		resourceSet.addUrl("tomcat-embed-websocket-8.5.9.jar", impl.getLibraryPath("tomcat-embed-websocket-8.5.9.jar"));
		root.addJarResources(resourceSet);
				
		try(AppBaseWebappClassLoader classLoader = new AppBaseWebappClassLoader(context);){
			classLoader.loadClass("com.acme.rest.TestObject");
		}
	}
	
	@Test
	public void runTest() throws Exception {
		ClasspathResourcesImpl impl = new ClasspathResourcesImpl();
		impl.loadClassPath();
		TestContext context = new TestContext();
		
		WebResourceRoot root = context.getResources();
		AppBaseVirtualDirectoryResourceSet resourceSet = new AppBaseVirtualDirectoryResourceSet(root, "/WEB-INF/lib", "/repo", "/");
		resourceSet.addUrl("tomcat-embed-el-8.5.9.jar", impl.getLibraryPath("tomcat-embed-el-8.5.9.jar"));
		resourceSet.addUrl("tomcat-embed-websocket-8.5.9.jar", impl.getLibraryPath("tomcat-embed-websocket-8.5.9.jar"));
		root.addJarResources(resourceSet);
		root.start();
		try(AppBaseWebappClassLoader classLoader = new AppBaseWebappClassLoader(context);){
			classLoader.start();
			
			Class<?> clazz1 = classLoader.loadClass("com.quakearts.appbase.Main");
			Class<?> clazz2 = Class.forName("com.quakearts.appbase.Main");
			
			assertThat(clazz1 == clazz2, is(true));
			
			Class<?> class3 = classLoader.loadClass("com.acme.rest.TestObject");
			
			assertThat(class3.getClassLoader(), is(classLoader));
			
			try {
				Class.forName("com.acme.rest.TestObject");
				fail("Did not throw ClassNotFoundException");
			} catch (ClassNotFoundException e) {
			}
			
			int count = 0;
			
			Enumeration<URL> resources = classLoader.getResources("META-INF/services/"+ServletContainerInitializer.class.getName());
			while (resources.hasMoreElements()) {
				URL resource = (URL) resources.nextElement();
				assertThat(resource, is(notNullValue()));
				count++;
			}
			
			assertThat(count, is(1));
			
			resources = classLoader.getResources("java.sql.Driver");
			assertThat(resources.hasMoreElements(), is(false));
		}
	}

}
