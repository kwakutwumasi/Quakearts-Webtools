/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.webapp.orm.cdi.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

/**Decorates an injection point that requires a {@link com.quakearts.webapp.orm.DataStore} instance
 * produced by a {@link com.quakearts.webapp.orm.DataStoreFactory} factory method
 * @author kwaku
 *
 */
@Qualifier
@Retention(RUNTIME)
public @interface DataStoreHandle {
	@Nonbinding String value() default "";
}
