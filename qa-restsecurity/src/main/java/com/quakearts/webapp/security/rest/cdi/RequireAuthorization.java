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
package com.quakearts.webapp.security.rest.cdi;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

/**Annotation for methods/classes that require authorization.
 * @author kwakutwumasi-afriyie
 *
 */
@InterceptorBinding
@Retention(RUNTIME)
@Target({METHOD,TYPE})
public @interface RequireAuthorization {
	/**List of roles to allow
	 * @return roles to allow
	 */
	@Nonbinding
	String[] allow() default {};
	/**List of roles to deny
	 * @return roles to deny
	 */
	@Nonbinding
	String[] deny() default {};
}
