package com.quakearts.classpathscanner.listener;

/**
 * The Interface ClassAnnotationDiscoveryListener.
 * 
 * @author animesh.kumar
 */
public interface ClassAnnotationScanningListener extends AnnotationScanningListener {

	/**
	 * Gets called by the Discoverer with class-name of the class where annotation is found.
	 * 
	 * @param clazz			
	 * @param annotation
	 */
	void discovered(String className, String annotation);
}
