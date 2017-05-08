/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.common.exceptionhandler;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.quakearts.classannotationscanner.ClasspathScanner;
import com.quakearts.classannotationscanner.Scanner;
import com.quakearts.common.exceptionhandler.scannerlistener.HandlerAnnotationListener;
import com.quakearts.common.exceptionhandler.scannerlistener.HandlersAnnotationListener;

/**Default handler factory. Uses a ConcurrentHashMap as the backing store. Uses {@link Scanner} to 
 * scan class files for {@link ExceptionHandler} implementations annotated with the {@link com.quakearts.common.exceptionhandler.annotations.Handler Handler}
 * annotation
 * @author Kwaku Twumasi
 *
 */
public class DefaultExceptionHandlerFactory extends ExceptionHandlerFactory {

	private static Map<ExceptionHandlerKey, ExceptionHandler> handlerRegistry = new ConcurrentHashMap<>();
	private static Boolean dontscan = false;
	
	protected DefaultExceptionHandlerFactory() {
		scan();
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.common.exceptionhandler.ExceptionHandlerFactory#get(com.quakearts.common.exceptionhandler.ExceptionHandlerKey)
	 */
	@Override
	protected ExceptionHandler get(ExceptionHandlerKey key) {
		return handlerRegistry.get(key);
	}

	/* (non-Javadoc)
	 * @see com.quakearts.common.exceptionhandler.ExceptionHandlerFactory#store(com.quakearts.common.exceptionhandler.ExceptionHandlerKey, com.quakearts.common.exceptionhandler.ExceptionHandler)
	 */
	@Override
	protected void store(ExceptionHandlerKey key, ExceptionHandler handler) {
		handlerRegistry.put(key, handler);
	}

	/* (non-Javadoc)
	 * @see com.quakearts.common.exceptionhandler.ExceptionHandlerFactory#getEntries()
	 */
	@Override
	protected Collection<Entry<ExceptionHandlerKey, ExceptionHandler>> getEntries() {
		return handlerRegistry.entrySet();
	}

	/* (non-Javadoc)
	 * @see com.quakearts.common.exceptionhandler.ExceptionHandlerFactory#hasKey(com.quakearts.common.exceptionhandler.ExceptionHandlerKey)
	 */
	@Override
	protected boolean hasKey(ExceptionHandlerKey key) {
		return handlerRegistry.containsKey(key);
	}

	/**Method to initiate classpath scanning. This is the default behavior.
	 * 
	 */
	protected void scan() {
		synchronized (dontscan) {
			if(!dontscan){//prevent multiple scans. Just in case
				Scanner scanner = getScanner();
				addScanAnnotationListeners(scanner);
				scanner.scan();
				dontscan = true;
			}			
		}
	}
	
	/**Gets the scanner to use for scanning
	 * {@link ExceptionHandlerFactory} implementations may choose to simply override this method to provide 
	 * more robust handler storage (e.g. to a Database, a distributed cache, etc)
	 * or implement their own {@link ExceptionHandlerFactory} behavior
	 * @return
	 */
	protected Scanner getScanner() {
		return new ClasspathScanner();
	}

	/**Convenience method to simply plug the listeners to an existing scanner. Useful for projects that use {@link Scanner}
	 * for loading other components to streamline scanning
	 * @param scanner
	 */
	public static void addScanAnnotationListeners(Scanner scanner) {
		dontscan = true;
		scanner.addAnnotationListener(new HandlerAnnotationListener())
			.addAnnotationListener(new HandlersAnnotationListener());
	}
}
