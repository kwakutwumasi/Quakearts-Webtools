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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.Manifest;

import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.WebResource;
import org.apache.catalina.WebResourceRoot;
import com.quakearts.appbase.Main;

public class AppBaseWebappClassLoader extends URLClassLoader implements Lifecycle {

	public AppBaseWebappClassLoader(Context context) {
		this(context, Main.class.getClassLoader());
	}

	public AppBaseWebappClassLoader(Context context, ClassLoader parent) {
		super(new URL[0], parent);
		this.context = context;
	}

	private WebResourceRoot resources = null;
	private Context context;
	private volatile LifecycleState state = LifecycleState.NEW;
	private static final String CLASS_FILE_SUFFIX = ".class";

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		checkStateForClassLoading(name);
		return findClassInternal(name);
	}

	@Override
	public URL findResource(String name) {
		checkStateForResourceLoading(name);

		URL url = null;

		String path = nameToPath(name);

		WebResource resource = resources.getClassLoaderResource(path);
		if (resource.exists()) {
			url = resource.getURL();
		}

		return url;
	}

	@Override
	public Enumeration<URL> findResources(String name) throws IOException {
		checkStateForResourceLoading(name);

		LinkedHashSet<URL> result = new LinkedHashSet<>();

		String path = nameToPath(name);

		WebResource[] webResources = resources.getClassLoaderResources(path);
		for (WebResource webResource : webResources) {
			if (webResource.exists()) {
				result.add(webResource.getURL());
			}
		}

		return Collections.enumeration(result);
	}

	@Override
	public URL getResource(String name) {
		return findResource(name);
	}

	@Override
	public InputStream getResourceAsStream(String name) {
		checkStateForResourceLoading(name);
		InputStream stream = null;
		String path = nameToPath(name);
		WebResource resource = resources.getClassLoaderResource(path);
		if (resource.exists()) {
			stream = resource.getInputStream();
		}

		return stream;
	}

	// Limit search to just exposed libraries from listed libraries
	@Override
	public Enumeration<URL> getResources(String name) throws IOException {
		return findResources(name);
	}

	private String nameToPath(String name) {
		if (name.startsWith("/")) {
			return name;
		}
		StringBuilder path = new StringBuilder(1 + name.length());
		path.append('/');
		path.append(name);
		return path.toString();
	}

	protected Class<?> findClassInternal(String name) throws ClassNotFoundException {
		checkStateForResourceLoading(name);

		if (name == null) {
			return null;
		}
		String path = binaryNameToPath(name, true);

		WebResource resource = resources.getClassLoaderResource(path);

		if (resource == null) {
			throw new ClassNotFoundException(name + " cannot be found");
		}

		if (!resource.exists()) {
			throw new ClassNotFoundException(name + " cannot be found");
		}

		Class<?> clazz;
		synchronized (getClassLoadingLock(name)) {
			byte[] binaryContent = resource.getContent();
			Manifest manifest = resource.getManifest();
			URL codeBase = resource.getCodeBase();
			Certificate[] certificates = resource.getCertificates();

			// Looking up the package
			String packageName = null;
			int pos = name.lastIndexOf('.');
			if (pos != -1)
				packageName = name.substring(0, pos);

			Package pkg = null;

			if (packageName != null) {
				pkg = getPackage(packageName);
				// Define the package (if null)
				if (pkg == null) {
					try {
						if (manifest == null) {
							definePackage(packageName, null, null, null, null, null, null, null);
						} else {
							definePackage(packageName, manifest, codeBase);
						}
					} catch (IllegalArgumentException e) {
						// Ignore: normal error due to dual definition of package
					}
				}
			}

			clazz = defineClass(name, binaryContent, 0, binaryContent.length, new CodeSource(codeBase, certificates));
		}

		return clazz;
	}

	Map<URL, Boolean> urls = new ConcurrentHashMap<>();

	@Override
	public URL[] getURLs() {
		return urls.keySet().toArray(new URL[urls.size()]);
	}

	@Override
	protected void addURL(URL url) {
		urls.put(url, Boolean.TRUE);
	}

	private String binaryNameToPath(String binaryName, boolean withLeadingSlash) {
		// 1 for leading '/', 6 for ".class"
		StringBuilder path = new StringBuilder(7 + binaryName.length());
		if (withLeadingSlash) {
			path.append('/');
		}
		path.append(binaryName.replace('.', '/'));
		path.append(CLASS_FILE_SUFFIX);
		return path.toString();
	}

	protected void checkStateForResourceLoading(String resource) {
		if (!state.isAvailable()) {
			throw new IllegalStateException("ClassLoader is not available");
		}
	}

	protected void checkStateForClassLoading(String className) throws ClassNotFoundException {
		try {
			checkStateForResourceLoading(className);
		} catch (IllegalStateException ise) {
			throw new ClassNotFoundException(ise.getMessage(), ise);
		}
	}

	@Override
	public void addLifecycleListener(LifecycleListener listener) {
		// Do nothing
	}

	@Override
	public LifecycleListener[] findLifecycleListeners() {
		return new LifecycleListener[0];
	}

	@Override
	public void removeLifecycleListener(LifecycleListener listener) {
		// Do nothing
	}

	@Override
	public void init() throws LifecycleException {
		// Do nothing
	}

	@Override
	public void start() throws LifecycleException {
		state = LifecycleState.STARTING_PREP;
		resources = context.getResources();
		WebResource classes = resources.getResource("/WEB-INF/classes");
		if (classes.isDirectory() && classes.canRead()) {
			addURL(classes.getURL());
		}

		WebResource[] jars = resources.listResources("/WEB-INF/lib");
		for (WebResource jar : jars) {
			if (jar.getName().endsWith(".jar") && jar.isFile() && jar.canRead()) {
				addURL(jar.getURL());
			}
		}

		state = LifecycleState.STARTED;
	}

	@Override
	public void stop() throws LifecycleException {
		urls.clear();
		state = LifecycleState.STOPPED;
	}

	@Override
	public void destroy() {
		state = LifecycleState.DESTROYING;
		try {
			super.close();
		} catch (IOException ioe) {
			// Do nothing
		}
		state = LifecycleState.DESTROYED;
	}

	@Override
	public LifecycleState getState() {
		return state;
	}

	@Override
	public String getStateName() {
		return getState().toString();
	}
}
