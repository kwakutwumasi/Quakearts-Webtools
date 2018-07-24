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
package com.quakearts.common.exceptionhandler.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**Annotation to mark an {@link com.quakearts.common.exceptionhandler.ExceptionHandler ExceptionHandler}
 * implementation as auto-discoverable
 * @author kwakutwumasi-afriyie
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Handler {
	/**The Parent of exception classes to handle.
	 * @return
	 */
	Class<? extends Exception> exceptionClass();
	/**Calling class that the handler is registered for, if any
	 * @return
	 */
	Class<?> relatedClass() default Object.class;
}
