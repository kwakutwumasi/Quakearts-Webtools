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
package com.quakearts.common.exceptionhandler.scannerlistener;

import com.quakearts.classannotationscanner.listener.ClassAnnotationScanningListener;
import com.quakearts.common.exceptionhandler.ExceptionHandler;
import com.quakearts.common.exceptionhandler.ExceptionHandlerFactory;
import com.quakearts.common.exceptionhandler.annotations.Handler;
import com.quakearts.common.exceptionhandler.annotations.Handlers;
import com.quakearts.common.exceptionhandler.exceptions.ExceptionHandlerRuntimeException;

public class HandlersAnnotationListener implements ClassAnnotationScanningListener {

	@Override
	public String[] getAnnotationsToListenFor() {
		return new String[]{Handlers.class.getName()};
	}

	@Override
	public void handle(String className, String annotation) {
		try {
			Class<?> handlerClass = Class.forName(className);
			
			if(!ExceptionHandler.class.isAssignableFrom(handlerClass))
				throw new ExceptionHandlerRuntimeException("Class "+className+" does not implment com.quakearts.common.exceptionhandler.ExceptionHandler");
			
			Handlers handlersAnnotation = handlerClass.getAnnotation(Handlers.class);
			ExceptionHandler exceptionHandler = (ExceptionHandler) handlerClass.newInstance();
			for(Handler handlerAnnotation:handlersAnnotation.value()){
				ExceptionHandlerFactory.registerExceptionHandler(handlerAnnotation.exceptionClass(),
								handlerAnnotation.relatedClass() == Object.class ? null : handlerAnnotation.relatedClass(),
								exceptionHandler);
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new ExceptionHandlerRuntimeException(e);
		}
	}

}
