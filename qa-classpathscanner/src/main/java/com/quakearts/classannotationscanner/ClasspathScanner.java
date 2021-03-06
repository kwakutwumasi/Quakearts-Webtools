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
package com.quakearts.classannotationscanner;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import com.quakearts.classannotationscanner.exception.ScannerRuntimeException;
import com.quakearts.classannotationscanner.resource.ClassFileIterator;
import com.quakearts.classannotationscanner.resource.JarFileIterator;
import com.quakearts.classannotationscanner.resource.ResourceInputStreamIterator;

/** Main implementation of Scanner. Looks for annotations in jar files and class folders on the current classpath
 * @author Kwaku Twumasi Afriyie (kwaku.twumasi@quakearts.com)
 *
 */
public class ClasspathScanner extends URLResourceScanner {

    /**Filter used to skip loading of classes from popular libraries and library vendors
     * 
     */
    private Filter filter;

    /**Default constructor. Uses the default filter implementation {@link DefaultFilter}.
     * 
     */
    public ClasspathScanner() {
        filter = new DefaultFilter();
    }
    
    /**Constructor for injecing a custom filter
     * @param filter The {@link Filter} implementations.
     */
    public ClasspathScanner(Filter filter) {
		this.filter = filter;
	}
    
    /**Constructor for restricting scanning to a single package
     * @param packageName
     */
    public ClasspathScanner(String... packageName) {
		this.filter = new PackageFilter(packageName);
	}

	/* (non-Javadoc)
	 * @see com.quakearts.classpathscanner.ScannerImpl#findResources()
	 */
	@Override
    protected final URL[] findResources() {
		Set<URI> uris = new HashSet<>();
        URI[] ret = getUrlsForCurrentClasspath();
        uris.addAll(Arrays.asList(ret));
        ret = getUrlsForSystemClasspath();
        uris.addAll(Arrays.asList(ret));
        return uris.stream().map(this::toUrl)
        		.collect(Collectors.toList()).toArray(new URL[0]);
    }

	private URL toUrl(URI url) {
		try {
			return url.toURL();
		} catch (MalformedURLException e) {
			throw new ScannerRuntimeException(e);
		}
	}

    /* (non-Javadoc)
     * @see com.quakearts.classpathscanner.ScannerImpl#getResourceIterator(java.net.URI, com.quakearts.classpathscanner.Filter)
     */
    @Override
    protected ResourceInputStreamIterator getResourceIterator(URL url, Filter filter) throws IOException {
        String urlString = url.toString();
        if (urlString.endsWith("!/")) {
            urlString = urlString.substring(4);
            urlString = urlString.substring(0, urlString.length() - 2);
            url = new URL(urlString);
        }

        if (!urlString.endsWith("/")) {
            return new JarFileIterator(url.openStream(), filter);
        } else {

            if (!url.getProtocol().equals("file")) {
                throw new IOException("Unable to understand protocol: " + url.getProtocol());
            }

            String filePath = URLDecoder.decode(url.getPath(), "UTF-8");
            File f = new File(filePath);
            if (!f.exists()) return null;

            if (f.isDirectory()) {
                return new ClassFileIterator(f, filter);
            } else {
                return new JarFileIterator(url.openStream(), filter);
            }
        }
    }
    
    /* (non-Javadoc)
     * @see com.quakearts.classpathscanner.ScannerImpl#getFilter()
     */
    @Override
    public final Filter getFilter() {
        return filter;
    }

    /**Setter method for filter
     * @param filter
     */
    public final void setFilter(Filter filter) {
        this.filter = filter;
    }

    /**Looks for {@link URI}s from the implementations of {@link URLClassLoader} in the current class loader hierarchy.
     * @return an array of {@link URI}s to iterate over
     */
    private URI[] getUrlsForCurrentClasspath() {
        Set<URI> list = new HashSet<>();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        while (loader != null) {
            if (loader instanceof URLClassLoader) {
                URL[] urlArray = ((URLClassLoader) loader).getURLs();
                for(URL url:urlArray) {
					try {
						list.add(url.toURI());
					} catch (URISyntaxException e) {
						throw new ScannerRuntimeException(e);
					}
                }
            }
            loader = loader.getParent();
        }
        return list.toArray(new URI[list.size()]);
    }

    /**Looks for {@link URI}s from the system class path
     * @return an array of {@link URI}s to iterate over
     */
    private URI[] getUrlsForSystemClasspath() {
        Set<URI> list = new HashSet<>();
        String classpath = System.getProperty("java.class.path");
        StringTokenizer tokenizer = new StringTokenizer(classpath,
                File.pathSeparator);

        while (tokenizer.hasMoreTokens()) {
            String path = tokenizer.nextToken();

            File fp = new File(path);
            if (!fp.exists())
                throw new ScannerRuntimeException(
                        "File in java.class.path does not exist: " + fp);
            list.add(fp.toURI());
        }
        return list.toArray(new URI[list.size()]);
    }
}
