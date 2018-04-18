/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.webtools.resteasy.cdi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.Provider;

public class UtilityMethods {

	public static boolean isJaxrsAnnotatedClass(Class<?> clazz) {
		if (clazz.isAnnotationPresent(Path.class)) {
			return true;
		}
		if (clazz.isAnnotationPresent(Provider.class)) {
			return true;
		}
		for (Method method : clazz.getMethods()) {
			if (method.isAnnotationPresent(Path.class)) {
				return true;
			}
			for (Annotation annotation : method.getAnnotations()) {
				if (annotation.annotationType().isAnnotationPresent(HttpMethod.class)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isJaxrsResource(Class<?> clazz) {
		if (isJaxrsAnnotatedClass(clazz)) {
			return true;
		}
		for (Class<?> intf : clazz.getInterfaces()) {
			if (isJaxrsAnnotatedClass(intf)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isJaxrsComponent(Class<?> clazz) {
		return ((clazz.isAnnotationPresent(Provider.class)) || (isJaxrsResource(clazz))
				|| (Application.class.isAssignableFrom(clazz)));
	}

	public static boolean isScopeDefined(AnnotatedType<?> annotatedType, BeanManager manager) {
		for (Annotation annotation : annotatedType.getAnnotations()) {
			if (manager.isScope(annotation.annotationType())) {
				return true;
			}
			if (manager.isStereotype(annotation.annotationType())) {
				if (isScopeDefined(annotation.annotationType(), manager)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isScopeDefined(Class<?> clazz, BeanManager manager) {
		for (Annotation annotation : clazz.getAnnotations()) {
			if (manager.isScope(annotation.annotationType())) {
				return true;
			}
			if (manager.isStereotype(annotation.annotationType())) {
				if (isScopeDefined(annotation.annotationType(), manager)) {
					return true;
				}
			}
		}
		return false;
	}
}
