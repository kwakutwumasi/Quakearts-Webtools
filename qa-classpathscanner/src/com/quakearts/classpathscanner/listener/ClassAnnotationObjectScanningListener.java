package com.quakearts.classpathscanner.listener;

import javassist.bytecode.ClassFile;
import javassist.bytecode.annotation.Annotation;

/**
 * The Interface ClassAnnotationDiscoveryListener.
 *
 */
public interface ClassAnnotationObjectScanningListener extends AnnotationScanningListener {

	/**
	 * Gets called by the Discoverer with class-name of the class where annotation is found.
	 *
	 * @param clazz
	 * @param annotation
	 */
	void discovered(ClassFile clazz, Annotation annotation);
}
