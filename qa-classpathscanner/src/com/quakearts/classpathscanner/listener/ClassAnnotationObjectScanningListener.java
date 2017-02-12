package com.quakearts.classpathscanner.listener;

import javassist.bytecode.ClassFile;
import javassist.bytecode.annotation.Annotation;

/**Interface for {@link AnnotationScanningListener}s that need access to the {@link ClassFile} object.
 * @author Kwaku Twumasi Afriyie (kwaku.twumasi@quakearts.com)
 *
 */
public interface ClassAnnotationObjectScanningListener extends AnnotationScanningListener {
	/**Handle processing for the discovered class
	 * @param clazz The {@link ClassFile}
	 * @param annotation The {@link Annotation}
	 */
	void handle(ClassFile clazz, Annotation annotation);
}
