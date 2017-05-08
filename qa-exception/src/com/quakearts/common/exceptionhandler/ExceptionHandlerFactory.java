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
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.quakearts.common.exceptionhandler.annotations.Handler;
import com.quakearts.common.exceptionhandler.exceptions.ExceptionHandlerRuntimeException;

/**Base Class for all exception handler factories. Contains default implementation of factory methods.
 * The default implementation use a Map as the store for handlers. Extenders may wish to use a different store
 * or provide a mechanism for sharing handlers between VM's, or serialize handlers for storage in a database
 * @author Kwaku Twumasi
 *
 */
public abstract class ExceptionHandlerFactory {	
	private static final ExceptionHandler DEFAULTEXCEPTIONHANDLER = new DefaultExceptionHandler();
	protected static ExceptionHandlerFactory instance;	
	private static final Logger log = Logger.getLogger("ExceptionHandlerFactory");	
	
	/**Method to fetch handler for the given exceptionClass and relatedClass
	 * @param key {@link ExceptionHandlerKey} key pair
	 * @return The exception handler stored with this key or null if none exists
	 */
	protected abstract ExceptionHandler get(ExceptionHandlerKey key);
	/**Method to store the {@link ExceptionHandler} instance with the specified key
	 * @param key {@link ExceptionHandlerKey} key pair
	 * @param handler The {@link ExceptionHandler} to store
	 */
	protected abstract void store(ExceptionHandlerKey key, ExceptionHandler handler);
	/**Method to enumerate all the handler key value pairs stored
	 * @return The key-value entry pairs
	 */
	protected abstract Collection<Entry<ExceptionHandlerKey, ExceptionHandler>> getEntries();
	/**Method to check for the presence of an {@link ExceptionHandler} in the underlying store
	 * @param key 
	 * @return True if a handler with the specified key exists
	 */
	protected abstract boolean hasKey(ExceptionHandlerKey key);
		
	/**Find and retrieve a registered exception handler using the provided {@link StackTraceElement} StackTraceElement element.
	 * If no specific handler has been registered, find a registered handler whose exceptionClass and relatedClass are parents 
	 * of the provided exceptionClass and relatedClass. If none is found the default handler is used
	 * @param exceptionClass The exception class
	 * @param element The StackTraceElement used to determine related class
	 * @return The exception handler
	 */
	public ExceptionHandler fetchExceptionHandlerWithStackTrace(Class<? extends Exception> exceptionClass, StackTraceElement element){
		Class<?> callingClass = null;
		if(element!=null)
			try {
				callingClass = Class.forName(element.getClassName());
			} catch (ClassNotFoundException e) {
				// Should not happen
				throw new ExceptionHandlerRuntimeException("Some how the calling class cannot be loaded.");
			}
		
		ExceptionHandler handler = fetchExceptionHandlerWithRelatedClass(exceptionClass, callingClass);
		
		if(handler==DEFAULTEXCEPTIONHANDLER){
			log.warning("The call for the non existent handler was made from "+ (element!=null? element.getClassName() 
					+ " from method " + element.getMethodName()
					+" at line "+element.getLineNumber()+". Using default handler." :" the root class"));
		}
		
		return handler;
	}

	/**Find and retrieve a registered handler using the related class. If no specific handler has been registered,
	 * find a registered handler whose exceptionClass and relatedClass are parents of the provided exceptionClass and relatedClass.
	 * If none is found the default handler is used
	 * @param exceptionClass
	 * @param relatedClass
	 * @return The exception handler
	 */
	public ExceptionHandler fetchExceptionHandlerWithRelatedClass(Class<? extends Exception> exceptionClass, Class<?> relatedClass) {
		ExceptionHandler exceptionHandler = null;
		ExceptionHandlerKey candidateKey = new ExceptionHandlerKey(exceptionClass, relatedClass);
		exceptionHandler = get(candidateKey);
		
		if(exceptionHandler == null){
			exceptionHandler = findAssignableHandler(exceptionClass, relatedClass);
		}
		
		return exceptionHandler;
	}
	
	/**Find and retrieve a registered handler. The handler must be specified without a relatedClass. If no specific handler has been 
	 * registered, find a registered handler whose exceptionClass is a parent of the provided exceptionClass. 
	 * If none is found the default handler is used
	 * @param exceptionClass
	 * @return The exception handler
	 */
	public ExceptionHandler fetchExceptionHandler(Class<? extends Exception> exceptionClass){
		ExceptionHandler exceptionHandler = null;
		ExceptionHandlerKey candidateKey = new ExceptionHandlerKey(exceptionClass, null);
		
		exceptionHandler = get(candidateKey);
	
		if(exceptionHandler == null){
			exceptionHandler = findAssignableHandler(exceptionClass, null);
		}
		
		return exceptionHandler;
	}
	
	private ExceptionHandler findAssignableHandler(Class<? extends Exception> exceptionClass, Class<?> relatedClass){
		ExceptionHandler exceptionHandler = null;
		for(Entry<ExceptionHandlerKey, ExceptionHandler> entry: getEntries()){
			if((relatedClass == null || (entry.getKey().relatedClass !=null 
					&& entry.getKey().relatedClass.isAssignableFrom(relatedClass)))&& 
					entry.getKey().exceptionClass.isAssignableFrom(exceptionClass)){
				exceptionHandler = entry.getValue();
				break;
			}
		}

		if(exceptionHandler == null && relatedClass !=null){
			ExceptionHandlerKey candidateKey = new ExceptionHandlerKey(exceptionClass, null);
			exceptionHandler = get(candidateKey);
		}
		
		if(exceptionHandler == null){
			exceptionHandler = DEFAULTEXCEPTIONHANDLER;
			log.warning("Unable to find a handler for " + exceptionClass.getName()
					+". Using default handler.");
		}
		
		//to make it faster next time
		store(new ExceptionHandlerKey(exceptionClass, relatedClass), exceptionHandler);
		
		return exceptionHandler;
	}

	/**Register an exception handler
	 * @param exceptionClass
	 * @param relatedClass
	 * @param exceptionHandler
	 */
	public static void registerExceptionHandler(Class<? extends Exception> exceptionClass, Class<?> relatedClass, ExceptionHandler exceptionHandler){
		if(exceptionClass == null || exceptionHandler == null)
			throw new ExceptionHandlerRuntimeException("Parameter "+(exceptionClass == null?"exceptionClass":"exceptionHandler")+" is required");
		
		ExceptionHandlerKey key = new ExceptionHandlerKey(exceptionClass, relatedClass);
		if(getInstance().hasKey(key)){
			log.warning("Another handler has already been registered for exception class " + exceptionClass.getName()
					+ (relatedClass != null ? ", calling class " + relatedClass.getName() : "")+". "
					+ getInstance().get(key).getClass().getName() + " will be overidden by "
					+ exceptionHandler.getClass().getName()
					+ ". Review your code to ensure each handler is unique.");
		}
		
		getInstance().store(key, exceptionHandler);
	}
	
	/**Method for registering the ExceptionHandlerFactory implementation. Must only be called once per class loaded instance
	 * If called more than once a ExceptionHandlerRuntimeException is thrown. Can be called from a main method or a
	 * setup thread to register the default implementation of ExceptionHandlerFactory to be used in the application
	 * @param instance The ExceptionHandlerFactory implementation
	 */
	protected static void setInstance(ExceptionHandlerFactory instance) {
		if(instance == null)
			throw new ExceptionHandlerRuntimeException("instance cannot be null");
		
		if(ExceptionHandlerFactory.instance == null)
			ExceptionHandlerFactory.instance = instance;
		else
			throw new ExceptionHandlerRuntimeException("Only one factory can exist per application.");
	}
	
	/**Gets the {@link ExceptionHandlerFactory}. If no instance has has been set via the protected 
	 * {@link #setInstance(ExceptionHandlerFactory) setInstance()} method, the default factory {@link DefaultExceptionHandlerFactory}
	 * will be instantiated and its {@linkplain DefaultExceptionHandlerFactory#scan() scan} method will be called to search the 
	 * class path for classes annotated with the {@link Handler} annotation
	 * @return
	 */
	public static ExceptionHandlerFactory getInstance() {
		if(instance==null){
			instance = new DefaultExceptionHandlerFactory();
		}
		
		return instance;
	}
}

/**A convenience class for indexing the exception handlers
 * @author Kwaku Twumasi
 *
 */
class ExceptionHandlerKey {
	Class<? extends Exception> exceptionClass;
	Class<?> relatedClass;
	
	public ExceptionHandlerKey(Class<? extends Exception> exceptionClass, Class<?> relatedClass) {
		this.exceptionClass = exceptionClass;
		this.relatedClass = relatedClass;
	}

	@Override
	public int hashCode() {
		if(exceptionClass!=null){
			return exceptionClass.hashCode()*5+ (relatedClass!=null?relatedClass.hashCode()*7:0);
		}
		return -1;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		
		if(obj == this)
			return true;
		
		if(!(obj instanceof ExceptionHandlerKey))
			return false;
		
		ExceptionHandlerKey comp = (ExceptionHandlerKey) obj;
		return (comp.relatedClass == relatedClass && comp.exceptionClass == exceptionClass);
	}
}
