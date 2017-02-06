package com.quakearts.classpathscanner;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * The Class ClasspathReader.
 *
 * @author animesh.kumar
 */
public class ClasspathScanner extends Scanner {

    /** The filter. */
    private Filter filter;

    /**
     * Instantiates a new classpath reader.
     */
    public ClasspathScanner() {
        filter = new FilterImpl();
    }

    /**
     * Uses java.class.path system-property to fetch URLs
     *
     * @return the URL[]
     */
    @Override
    public final URL[] findResources() {
        URL[] ret = getUrlsForCurrentClasspath();
        if (ret.length == 0) ret = getUrlsForSystemClasspath();
        return ret;
    }

    /* @see com.quakearts.annovention.Discoverer#getFilter() */
    public final Filter getFilter() {
        return filter;
    }

    /**
     * @param filter
     */
    public final void setFilter(Filter filter) {
        this.filter = filter;
    }

    //-------------------------------------------------------------------------

    // See http://code.google.com/p/reflections/source/browse/trunk/reflections/src/main/java/org/reflections/util/ClasspathHelper.java?r=103
    private URL[] getUrlsForCurrentClasspath() {
        List<URL> list = new ArrayList<URL>();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        while (loader != null) {
            if (loader instanceof URLClassLoader) {
                URL[]     urlArray = ((URLClassLoader) loader).getURLs();
                List<URL> urlList  = Arrays.asList(urlArray);
                list.addAll(urlList);
            }
            loader = loader.getParent();
        }
        return list.toArray(new URL[list.size()]);
    }

    private URL[] getUrlsForSystemClasspath() {
        List<URL> list = new ArrayList<URL>();
        String classpath = System.getProperty("java.class.path");
        StringTokenizer tokenizer = new StringTokenizer(classpath,
                File.pathSeparator);

        while (tokenizer.hasMoreTokens()) {
            String path = tokenizer.nextToken();

            File fp = new File(path);
            if (!fp.exists())
                throw new RuntimeException(
                        "File in java.class.path does not exist: " + fp);
            try {
                list.add(fp.toURI().toURL());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return list.toArray(new URL[list.size()]);
    }
}
