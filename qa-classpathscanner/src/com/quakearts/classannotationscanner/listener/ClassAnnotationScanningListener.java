package com.quakearts.classannotationscanner.listener;

import javassist.bytecode.annotation.Annotation;

/**Interface for {@link AnnotationScanningListener}s.
 * @author Kwaku Twumasi Afriyie (kwaku.twumasi@quakearts.com)
 *
 */
public interface ClassAnnotationScanningListener extends AnnotationScanningListener {
	/**Handle processing for the discovered class
	 * @param className The class name
	 * @param annotation The {@link Annotation}
	 */
	void handle(String className, String annotation);
}
