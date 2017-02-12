package com.quakearts.classpathscanner.listener;

import java.lang.annotation.Annotation;

/**Base interface for annotation listeners
 * @author Kwaku Twumasi Afriyie (kwaku.twumasi@quakearts.com)
 *
 */
public interface AnnotationScanningListener {

	/**A list of {@link Annotation} names that this listener handles
	 * @return
	 */
	String[] getAnnotationsToListenFor();
}
