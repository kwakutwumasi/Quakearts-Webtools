package com.quakearts.appbase.spi.impl.tomcat;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.jar.Manifest;
import java.util.stream.Collectors;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResource;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.webresources.AbstractResourceSet;
import org.apache.catalina.webresources.EmptyResource;
import org.apache.catalina.webresources.FileResource;

import com.quakearts.appbase.exception.ConfigurationException;

public class AppBaseVirtualDirectoryResourceSet extends AbstractResourceSet {

	private Map<String, URL> libraryUrls = new HashMap<>();

	public AppBaseVirtualDirectoryResourceSet(WebResourceRoot root, String webAppMount, String base,
			String internalPath) {
		setInternalPath(internalPath);
		setRoot(root);
		setWebAppMount(webAppMount);
		setBase(base);

		if (getRoot().getState().isAvailable()) {
			try {
				start();
			} catch (LifecycleException e) {
				throw new IllegalStateException(e);
			}
		}
	}

	public synchronized void addUrl(URL libraryPathUrl) {
		if (!libraryPathUrl.getProtocol().equals("file"))
			throw new ConfigurationException("libraryPathUrl must be a file url");

		String name = libraryPathUrl.getFile();
		name = libraryPathUrl.getFile();
		if (name.indexOf(File.separator) >= 0 && name.indexOf(File.separator) != name.length()) {
			name = name.substring(name.lastIndexOf(File.separator) + 1);
		}

		libraryUrls.put(name, libraryPathUrl);
	}

	public synchronized Collection<URL> getLibraryUrls() {
		return Collections.unmodifiableCollection(libraryUrls.values());
	}

    private static final String[] EMPTY_STRING_ARRAY = new String[0];

	@Override
	public WebResource getResource(String path) {
		checkPath(path);
		if (path.startsWith(getWebAppMount())) {
			String libraryName = path.substring(getWebAppMount().length());
			if(libraryName.startsWith("/"))
				libraryName = libraryName.substring(1);
			
			URL libraryUrl = libraryUrls.get(libraryName);
			if (libraryUrl != null) {
				File file;
				try {
					file = Paths.get(libraryUrl.toURI()).toFile();
				} catch (URISyntaxException e) {
					file = new File(libraryUrl.getPath());
				}
				
				return new FileResource(getRoot(), path, file, true, getManifest(libraryUrl));
			} else {
				return new EmptyResource(getRoot(), path);
			}
		} else {
			return new EmptyResource(getRoot(), path);
		}
	}

	private Manifest getManifest(URL libraryUrl) {
		try {
			URL jarUrl = new URL("jar:"+libraryUrl+"!/");
			JarURLConnection con = (JarURLConnection) jarUrl.openConnection();
			return con.getManifest();
		} catch (ClassCastException | IOException e) {
			throw new ConfigurationException("Unable to create jar url 'jar:"+libraryUrl+"!/'", e);
		}
	}

	@Override
	public String[] list(String path) {
        checkPath(path);
		if (path.startsWith(getWebAppMount())) {
			int diff = path.length() - getWebAppMount().length();
			if(diff >1
				|| (diff==1 && !path.endsWith("/")))
				return EMPTY_STRING_ARRAY;
			
			//Looking for all the files in this Virtual directory
			return libraryUrls.keySet().toArray(new String[libraryUrls.size()]);
		} else {
			return EMPTY_STRING_ARRAY;
		}
	}

	@Override
	public Set<String> listWebAppPaths(String path) {
        checkPath(path);
		if (path.startsWith(getWebAppMount())
				&& Math.abs(getWebAppMount().length()-path.length())==1
				&& path.endsWith("/")) {
			//Looking for all the files in this Virtual directory
			return libraryUrls.keySet()
					.stream().map((key)->{
						return path+key;
					}).collect(Collectors.toSet());
		} else {
			return Collections.emptySet();
		}
	}

	@Override
	public boolean mkdir(String path) {
		return false;
	}

	@Override
	public boolean write(String path, InputStream is, boolean overwrite) {
		return false;
	}

	@Override
	public URL getBaseUrl() {
		try {
			return new File(getBase()).toURI().toURL();
		} catch (MalformedURLException e) {
			throw new ConfigurationException("Unable to use file://repo as URL name", e);
		}
	}

	@Override
	public void setReadOnly(boolean readOnly) {
	}

	@Override
	public boolean isReadOnly() {
		return false;
	}

	@Override
	public void gc() {
		libraryUrls.clear();
	}

	@Override
	protected void initInternal() throws LifecycleException {
	}

}
