package com.quakearts.classpathscanner.listener;

/**
 * The Interface AnnotationDiscoveryListener.
 * 
 * @author animesh.kumar
 */
public interface AnnotationScanningListener {

	/**
	 * @return Array of supported annotations names
	 */
	String[] supportedAnnotations();
}
