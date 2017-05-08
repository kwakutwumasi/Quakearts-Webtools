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
package com.quakearts.syshub.core.metadata.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.quakearts.syshub.core.metadata.NameGenerator;
import com.quakearts.syshub.model.AgentConfigurationParameter.ParameterType;

@Retention(RUNTIME)
@Target(TYPE)
public @interface ConfigurationProperty {
	String value() default "";
	boolean required();
	ParameterType type();
	Class<? extends NameGenerator> generatorClass();
	String pattern();
	String description();
	String friendlyName();
	boolean password();
	Class<?> assignableType();

}
