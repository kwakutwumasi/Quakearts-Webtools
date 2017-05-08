/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.common.exceptionhandler;

/**Exception handling convenience methods. The methods call through to ExceptionHandlerFactory to find the handler and calls
 * its handleException method
 * 
 * @author QuakesHome
 *
 */
public class ExceptionHandlerUtil {
	private ExceptionHandlerUtil() {
	}
	
	/**Handle exception using the calling class as the related class. This makes it possible to create handlers
	 * specifically for a class. If a handler for the specified class is not found, an attempt will be made to
	 * find a handler whose registered exceptionClass and relatedClass are parents of the exception parameter
	 * and its related class
	 * @param e The exception to handle
	 * @param params Parameters required by the handler
	 */
	public static final void handleExceptionForThisClass(Exception e, Object...params){
		StackTraceElement[] elements = Thread.currentThread().getStackTrace();
		StackTraceElement element = null;
		if(elements.length>2){
			element = elements[2];
		}
		
		ExceptionHandler handler = ExceptionHandlerFactory.getInstance().fetchExceptionHandlerWithStackTrace(e.getClass(), element);
		handler.handleException(e, params);
	}
	
	/**Handle exception using a handler related to the specified class. If a handler for the specified class is not found, an attempt will be made to
	 * find a handler whose registered exceptionClass and relatedClass are parents of the exception parameter
	 * and its related class
	 * @param e The exception to handle
	 * @param relatedClass The related class
	 * @param params Parameters required by the handler
	 */
	public static final void handleExceptionWithRelatedClass(Exception e, Class<?> relatedClass, Object...params){
		ExceptionHandler handler = ExceptionHandlerFactory.getInstance().fetchExceptionHandlerWithRelatedClass(e.getClass(), relatedClass);
		handler.handleException(e, params);		
	}

	/**Handle exception. This method looks for general handlers and is the simplest to use. Handlers must not be 
	 * registered with related classes. If a handler for the specified class is not found, an attempt will be made to
	 * find a handler whose registered exceptionClass is a parent of the exception parameter
	 * @param e The exception to handle
	 * @param params Parameters required by the handler
	 */
	public static final void handleException(Exception e, Object...params){
		ExceptionHandler handler = ExceptionHandlerFactory.getInstance().fetchExceptionHandler(e.getClass());
		handler.handleException(e, params);		
	}
}
