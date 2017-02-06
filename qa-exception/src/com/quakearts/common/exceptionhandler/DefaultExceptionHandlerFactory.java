package com.quakearts.common.exceptionhandler;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.quakearts.classpathscanner.ClasspathScanner;
import com.quakearts.common.exceptionhandler.scannerlistener.HandlerAnnotationListener;
import com.quakearts.common.exceptionhandler.scannerlistener.HandlersAnnotationListener;

/**Default handler factory. Uses a ConcurrentHashMap as the backing store
 * @author Kwaku Twumasi
 *
 */
public class DefaultExceptionHandlerFactory extends ExceptionHandlerFactory {

	private static Map<ExceptionHandlerKey, ExceptionHandler> handlerRegistry = new ConcurrentHashMap<>();
	
	@Override
	protected ExceptionHandler get(ExceptionHandlerKey key) {
		return handlerRegistry.get(key);
	}

	@Override
	protected void store(ExceptionHandlerKey key, ExceptionHandler handler) {
		handlerRegistry.put(key, handler);
	}

	@Override
	protected Collection<Entry<ExceptionHandlerKey, ExceptionHandler>> getEntries() {
		return handlerRegistry.entrySet();
	}

	@Override
	protected boolean hasKey(ExceptionHandlerKey key) {
		return handlerRegistry.containsKey(key);
	}

	/**Method to initiate classpath scanning. This is the default behavior.
	 * {@link ExceptionHandlerFactory} implementations may choose to simply override this class to provide 
	 * more robust handler storage (e.g. to a Database, a distributed cache, etc)
	 * or implement their own {@link ExceptionHandlerFactory} behavior
	 */
	protected void scan() {
		ClasspathScanner classpathScanner = new ClasspathScanner();
		addScanAnnotationListeners(classpathScanner);
		classpathScanner.scan();
	}
	
	/**Convenience method to simply plug the listeners to an existing scanner. Useful for projects that use {@link ClasspathScanner}
	 * to streamline scanning
	 * @param classpathScanner
	 */
	public void addScanAnnotationListeners(ClasspathScanner classpathScanner) {
		classpathScanner.addAnnotationListener(new HandlerAnnotationListener());
		classpathScanner.addAnnotationListener(new HandlersAnnotationListener());
	}
}
