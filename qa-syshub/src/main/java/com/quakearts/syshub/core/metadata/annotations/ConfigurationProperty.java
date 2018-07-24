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
package com.quakearts.syshub.core.metadata.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.quakearts.syshub.model.AgentConfigurationParameter.ParameterType;

/**Metadata for module configuration properties
 * @author kwakutwumasi-afriyie
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ConfigurationProperty {
	/**The property name
	 * @return the property name
	 */
	String value();
	/**Attribute indicating that the property is required
	 * @return true iff the property is required
	 */
	boolean required() default false;
	/**The {@linkplain ParameterType}
	 * @return the {@linkplain ParameterType}
	 */
	ParameterType type() default ParameterType.STRING;
	/**A collection of string options to display for the property
	 * @return the list of options
	 */
	String[] optionList() default {};
	/**The pattern to validate the property
	 * @return the pattern
	 */
	String pattern() default "";
	/**The property description
	 * @return the description
	 */
	String description() default "";
	/**Friendly name for display
	 * @return the property
	 */
	String friendlyName() default "";
	/**The class that should be the parent of the class name value of the property
	 * @return the class
	 */
	Class<?> assignableType() default Object.class;
	/**Attribute indicating that the property is global
	 * @return true iff the property is global
	 */
	boolean global() default false;
}
