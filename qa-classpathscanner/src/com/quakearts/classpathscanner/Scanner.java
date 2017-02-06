package com.quakearts.classpathscanner;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.annotation.Annotation;

import com.quakearts.classpathscanner.listener.ClassAnnotationScanningListener;
import com.quakearts.classpathscanner.exception.ScannerRuntimeException;
import com.quakearts.classpathscanner.listener.ClassAnnotationObjectScanningListener;
import com.quakearts.classpathscanner.resource.ClassFileIterator;
import com.quakearts.classpathscanner.resource.JarFileIterator;
import com.quakearts.classpathscanner.resource.ResourceIterator;

/**
 * Base annotation discoverer.
 *
 * @author animesh.kumar
 */
public abstract class Scanner {

    /** map to hold ClassAnnotation listeners */
    private final Map<String, Set<ClassAnnotationScanningListener>> classAnnotationListeners =
        new HashMap<String, Set<ClassAnnotationScanningListener>>();

    /** map to hold ClassAnnotationObject listeners */
    private static final Map<String, Set<ClassAnnotationObjectScanningListener>> classAnnotationObjectListeners =
    	new HashMap<String, Set<ClassAnnotationObjectScanningListener>>();

    private static final Logger log = Logger.getLogger("Scanner");
    
    /**
     * Instantiates a new Discoverer.
     */
    public Scanner() {
    }

    /**
     * Adds ClassAnnotationDiscoveryListener
     *
     * @param listener
     */
    public final void addAnnotationListener (ClassAnnotationScanningListener listener) {
        addAnnotationListener (classAnnotationListeners, listener, listener.supportedAnnotations());
    }

    /**
     * Adds ClassAnnotationObjectDiscoveryListener
     *
     * @param listener
     */
    public final void addAnnotationListener (ClassAnnotationObjectScanningListener listener) {
    	addAnnotationListener (classAnnotationObjectListeners, listener, listener.supportedAnnotations());
    }

    /**
     * Helper class to find supported annotations of a listener and register them
     *
     * @param <L>
     * @param map
     * @param listener
     * @param annotations
     */
    private <L> void addAnnotationListener (Map<String, Set<L>> map, L listener, String... annotations) {
        // throw exception if the listener doesn't support any annotations. what's the point of
        // registering then?
        if (null == annotations || annotations.length == 0) {
            throw new IllegalArgumentException(listener.getClass() + " has no supporting Annotations. Check method supportedAnnotations");
        }

        for (String annotation : annotations) {
            Set<L> listeners = map.get(annotation);
            if (null == listeners) {
                listeners = new HashSet<L>();
                map.put(annotation, listeners);
            }
            listeners.add(listener);
        }
    }

    /**
     * Gets the filter implementation.
     *
     * @return the filter
     */
    public abstract Filter getFilter();

    /**
     * Finds resources to scan for
     */
    public abstract URL[] findResources();

    /**
     * that's my buddy! this is where all the discovery starts.
     */
    public final void scan() {
    	long starttime = System.nanoTime();
        URL[] resources = findResources();
        for (URL resource : resources) {
            try {
                ResourceIterator itr = getResourceIterator(resource, getFilter());
                if (itr != null) {
                    InputStream is = null;
                    while ((is = itr.next()) != null) {
                        // make a data input stream
                        DataInputStream dstream = new DataInputStream(new BufferedInputStream(is));
                        try {
                            // get java-assist class file
                            ClassFile classFile = new ClassFile(dstream);

                            // discover class-level annotations
                            scanAndNotifyForClassAnnotations (classFile);
                        } finally {
                             dstream.close();
                             is.close();
                        }
                    }
                }
            } catch (IOException e) {
                throw new ScannerRuntimeException(e);
            }
        }
    	log.info("Annotation scanning completed in "+((System.nanoTime()-starttime)/1000000)+" ms");
    }

    /**
     * Discovers Class Annotations
     *
     * @param classFile
     */
    private void scanAndNotifyForClassAnnotations(ClassFile classFile) {
        Set<Annotation> annotations = new HashSet<Annotation>();

		AnnotationsAttribute visibleA = (AnnotationsAttribute) classFile.getAttribute(AnnotationsAttribute.visibleTag);
        if (visibleA != null) annotations.addAll(Arrays.asList(visibleA.getAnnotations()));

        // now tell listeners
        for (Annotation annotation : annotations) {
        	// String versions of listeners
			Set<ClassAnnotationScanningListener> listeners = classAnnotationListeners.get(annotation.getTypeName());
			if (null != listeners) {
    			for (ClassAnnotationScanningListener listener : listeners) {
    				listener.discovered(classFile.getName(), annotation.getTypeName());
    			}
			}
			// Object versions of listeners
			Set<ClassAnnotationObjectScanningListener> olisteners = classAnnotationObjectListeners.get(annotation.getTypeName());
			if (null != olisteners) {
				for (ClassAnnotationObjectScanningListener listener : olisteners) {
					listener.discovered(classFile, annotation);
				}
			}
        }
    }

    /**
     * Gets the Resource iterator for URL with Filter.
     *
     * @param url
     * @param filter
     * @return
     * @throws IOException
     */
    private ResourceIterator getResourceIterator(URL url, Filter filter) throws IOException {
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


}
