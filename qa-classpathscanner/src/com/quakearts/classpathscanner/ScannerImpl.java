package com.quakearts.classpathscanner;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
import com.quakearts.classpathscanner.resource.ResourceInputStreamIterator;

/**Base class for {@link Scanner} implementations. Should be subclassed by all other implementations for ease of use
 * @author Kwaku Twumasi Afriyie (kwaku.twumasi@quakearts.com)
 *
 */
public abstract class ScannerImpl implements Scanner {

    private final Map<String, Set<ClassAnnotationScanningListener>> classAnnotationListeners =
        new HashMap<String, Set<ClassAnnotationScanningListener>>();

    private static final Map<String, Set<ClassAnnotationObjectScanningListener>> classAnnotationObjectListeners =
    	new HashMap<String, Set<ClassAnnotationObjectScanningListener>>();

    private static final Logger log = Logger.getLogger("Scanner");
    
    public ScannerImpl() {
    }

    /* (non-Javadoc)
	 * @see com.quakearts.classpathscanner.Scanner#addAnnotationListener(com.quakearts.classpathscanner.listener.ClassAnnotationScanningListener)
	 */
    @Override
	public final void addAnnotationListener (ClassAnnotationScanningListener listener) {
        addAnnotationListener (classAnnotationListeners, listener, listener.getAnnotationsToListenFor());
    }

    /* (non-Javadoc)
	 * @see com.quakearts.classpathscanner.Scanner#addAnnotationListener(com.quakearts.classpathscanner.listener.ClassAnnotationObjectScanningListener)
	 */
    @Override
	public final void addAnnotationListener (ClassAnnotationObjectScanningListener listener) {
    	addAnnotationListener (classAnnotationObjectListeners, listener, listener.getAnnotationsToListenFor());
    }

    private <L> void addAnnotationListener (Map<String, Set<L>> map, L listener, String... annotations) {
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

	/**Get an array of {@link URL}s to scan
	 * @return the array of {@link URL}s
	 */
	protected abstract URL[] findResources();

    /* (non-Javadoc)
	 * @see com.quakearts.classpathscanner.Scanner#scan()
	 */
    @Override
	public final void scan() {
    	long starttime = System.nanoTime();
        URL[] resources = findResources();
        for (URL resource : resources) {
            try {
                ResourceInputStreamIterator itr = getResourceIterator(resource, getFilter());
                if (itr != null) {
                    InputStream is = null;
                    while ((is = itr.next()) != null) {
                        DataInputStream dstream = new DataInputStream(new BufferedInputStream(is));
                        try {
                            ClassFile classFile = new ClassFile(dstream);
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

    private void scanAndNotifyForClassAnnotations(ClassFile classFile) {
        Set<Annotation> annotations = new HashSet<Annotation>();

		AnnotationsAttribute visibleA = (AnnotationsAttribute) classFile.getAttribute(AnnotationsAttribute.visibleTag);
        if (visibleA != null) annotations.addAll(Arrays.asList(visibleA.getAnnotations()));

        for (Annotation annotation : annotations) {
			Set<ClassAnnotationScanningListener> listeners = classAnnotationListeners.get(annotation.getTypeName());
			if (null != listeners) {
    			for (ClassAnnotationScanningListener listener : listeners) {
    				listener.handle(classFile.getName(), annotation.getTypeName());
    			}
			}

			Set<ClassAnnotationObjectScanningListener> objectListeners = classAnnotationObjectListeners.get(annotation.getTypeName());
			if (null != objectListeners) {
				for (ClassAnnotationObjectScanningListener listener : objectListeners) {
					listener.handle(classFile, annotation);
				}
			}
        }
    }

    protected abstract ResourceInputStreamIterator getResourceIterator(URL url, Filter filter) throws IOException;
}
