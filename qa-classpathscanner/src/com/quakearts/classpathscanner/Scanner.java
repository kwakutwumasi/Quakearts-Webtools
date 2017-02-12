package com.quakearts.classpathscanner;

import com.quakearts.classpathscanner.listener.ClassAnnotationObjectScanningListener;
import com.quakearts.classpathscanner.listener.ClassAnnotationScanningListener;

/**A scanner for discovering loadable classes by their annotations
 * @author Kwaku Twumasi Afriyie (kwaku.twumasi@quakearts.com)
 *
 */
public interface Scanner {
	/**Add an implementation of {@link ClassAnnotationScanningListener} to the list of listeners
	 * @param listener The listener
	 */
	void addAnnotationListener(ClassAnnotationScanningListener listener);
	/**Add an implementation of {@link ClassAnnotationObjectScanningListener} to the list of listeners
	 * @param listener The listener
	 */
	void addAnnotationListener(ClassAnnotationObjectScanningListener listener);
	/**Get the filter for this scanner
	 * @return The {@link Filter}
	 */
	Filter getFilter();
	/**Sets the filter for this scanner
	 * @param filter The {@link Filter}
	 */
	void setFilter(Filter filter);
	/**Scans for classes and executes listeners registered for the class annotations
	 * 
	 */
	void scan();
}