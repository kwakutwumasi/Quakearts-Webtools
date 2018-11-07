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
package com.quakearts.appbase.spi.impl.tomcat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.JarFactory;

import com.quakearts.appbase.exception.ConfigurationException;

public class AppBaseWebappServiceLoader<T> {

	public AppBaseWebappServiceLoader(Context context) {
		this.servletContext = context.getServletContext();
		String containerSciFilter = context.getContainerSciFilter();
		if (containerSciFilter != null && containerSciFilter.length() > 0) {
			containerSciFilterPattern = Pattern.compile(containerSciFilter);
		} else {
			containerSciFilterPattern = null;
		}
	}

	private static final String LIB = "/WEB-INF/lib/";
	private static final String SERVICES = "META-INF/services/";

	private final ServletContext servletContext;
	private final Pattern containerSciFilterPattern;

	public List<T> load(Class<T> serviceType) throws IOException {
		String configFile = SERVICES + serviceType.getName();

		LinkedHashSet<String> applicationServicesFound = new LinkedHashSet<>();
		LinkedHashSet<String> containerServicesFound = new LinkedHashSet<>();

		ClassLoader loader = servletContext.getClassLoader();

		if (loader == null) {
			throw new ConfigurationException("ServletContext.getClassLoader() did not return a classloader");
		}

		@SuppressWarnings("unchecked")
		List<String> orderedLibs = (List<String>) servletContext.getAttribute(ServletContext.ORDERED_LIBS);
		if (orderedLibs != null) {
			processOrderedLibraries(configFile, applicationServicesFound, orderedLibs);
		} else {
			Enumeration<URL> resources = loader.getResources(configFile); // Just load using our class loader
			// designed not to scan all libraries on the classpath
			while (resources.hasMoreElements()) {
				parseConfigFile(containerServicesFound, resources.nextElement());
			}
		}

		if (containerSciFilterPattern != null) {
			filterServletContainerInitializers(containerServicesFound);
		}

		// Add the application services after the container services to ensure
		// that the container services are loaded first
		containerServicesFound.addAll(applicationServicesFound);

		// load the discovered services
		if (containerServicesFound.isEmpty()) {
			return Collections.emptyList();
		}
		return loadServices(serviceType, containerServicesFound);
	}

	private void processOrderedLibraries(String configFile, LinkedHashSet<String> applicationServicesFound,
			List<String> orderedLibs) throws IOException {
		// The webapp has ordered web-fragments.xml or an <absolute-ordering> entry in
		// its web.xml
		// Honor that
		for (String lib : orderedLibs) {
			URL jarUrl = servletContext.getResource(LIB + lib);
			if (jarUrl == null) {
				continue;
			}

			String base = jarUrl.toExternalForm();
			URL url;
			if (base.endsWith("/")) {
				url = new URL(base + configFile);
			} else {
				url = JarFactory.getJarEntryURL(jarUrl, configFile);
			}
			try {
				parseConfigFile(applicationServicesFound, url);
			} catch (FileNotFoundException e) {
				//Do nothing
			}
		}
	}

	private void filterServletContainerInitializers(LinkedHashSet<String> containerServicesFound) {
		Iterator<String> iter = containerServicesFound.iterator();
		while (iter.hasNext()) {
			if (containerSciFilterPattern.matcher(iter.next()).find()) {
				iter.remove();
			}
		}
	}

	void parseConfigFile(LinkedHashSet<String> servicesFound, URL url) throws IOException {
		try (InputStream is = url.openStream();
				InputStreamReader in = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(in);) {
			String line;
			while ((line = reader.readLine()) != null) {
				int i = line.indexOf('#');
				if (i >= 0) {
					line = line.substring(0, i);
				}
				line = line.trim();
				if (line.length() == 0) {
					continue;
				}
				servicesFound.add(line);
			}
		}
	}

	List<T> loadServices(Class<T> serviceType, LinkedHashSet<String> servicesFound) throws IOException {
		ClassLoader loader = servletContext.getClassLoader();
		List<T> services = new ArrayList<>(servicesFound.size());
		for (String serviceClass : servicesFound) {
			try {
				Class<?> clazz = Class.forName(serviceClass, true, loader);
				services.add(serviceType.cast(clazz.newInstance()));
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException e) {
				throw new IOException(e);
			}
		}
		return Collections.unmodifiableList(services);
	}
}
