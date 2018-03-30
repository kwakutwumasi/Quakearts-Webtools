package com.quakearts.appbase.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsInstanceOf.*;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResource;
import org.apache.catalina.webresources.FileResource;
import org.apache.catalina.webresources.StandardRoot;
import org.junit.Test;

import com.quakearts.appbase.impl.ClasspathResourcesImpl;
import com.quakearts.appbase.spi.impl.tomcat.AppBaseVirtualDirectoryResourceSet;

public class TestAppBaseVirtualDirectoryResourceSet {

	@Test
	public void testStartStopDestroy() throws Exception {
		AppBaseVirtualDirectoryResourceSet resourceSet = new AppBaseVirtualDirectoryResourceSet(new StandardRoot(), "/", "/", "/");
		resourceSet.start();
		assertThat(resourceSet.getState().isAvailable(), is(true));
		resourceSet.stop();
		resourceSet.destroy();
	}

	@Test(expected=LifecycleException.class)
	public void testStopDestroyBeforeStop() throws Exception {
		AppBaseVirtualDirectoryResourceSet resourceSet = new AppBaseVirtualDirectoryResourceSet(new StandardRoot(), "/", "/", "/");
		resourceSet.start();
		resourceSet.destroy();
	}

	@Test
	public void testMethods() throws Exception {
		ClasspathResourcesImpl impl = new ClasspathResourcesImpl();
		impl.loadClassPath();
		StandardRoot root = new StandardRoot();
		AppBaseVirtualDirectoryResourceSet resourceSet = new AppBaseVirtualDirectoryResourceSet(root, "/WEB-INF/lib", "/repo", "/");
		resourceSet.addUrl("tomcat-embed-el-8.5.9.jar", impl.getLibraryPath("tomcat-embed-el-8.5.9.jar"));
		resourceSet.addUrl("tomcat-embed-websocket-8.5.9.jar", impl.getLibraryPath("tomcat-embed-websocket-8.5.9.jar"));
		resourceSet.start();
		
		assertThat(resourceSet.getLibraryUrls().size(), is(2));
		assertThat(resourceSet.getLibraryUrls().contains(impl.getLibraryPath("tomcat-embed-el-8.5.9.jar")), is(true));
		assertThat(resourceSet.getLibraryUrls().contains(impl.getLibraryPath("tomcat-embed-websocket-8.5.9.jar")), is(true));
		
		WebResource resource = resourceSet.getResource("/WEB-INF/lib/tomcat-embed-websocket-8.5.9.jar");
		assertThat(resource, is(notNullValue()));
		assertThat(resource, is(instanceOf(FileResource.class)));
		assertThat(resource.canRead(), is(true));
		assertThat(resource.isFile(), is(true));
		assertThat(resource.getURL(), is(impl.getLibraryPath("tomcat-embed-websocket-8.5.9.jar")));
		
		List<String> list = Arrays.asList(resourceSet.list("/WEB-INF/lib/"));
		assertThat(list.size(), is(2));
		assertThat(list.contains("tomcat-embed-el-8.5.9.jar"), is(true));
		assertThat(list.contains("tomcat-embed-websocket-8.5.9.jar"), is(true));
		
		assertThat(resourceSet.list("/WEB-INF/lib/resource").length, is(0));
		
		Set<String> set = resourceSet.listWebAppPaths("/WEB-INF/lib/");
		assertThat(set.size(), is(2));
		assertThat(set.contains("/WEB-INF/lib/tomcat-embed-el-8.5.9.jar"), is(true));
		assertThat(set.contains("/WEB-INF/lib/tomcat-embed-websocket-8.5.9.jar"), is(true));		
	}
}
