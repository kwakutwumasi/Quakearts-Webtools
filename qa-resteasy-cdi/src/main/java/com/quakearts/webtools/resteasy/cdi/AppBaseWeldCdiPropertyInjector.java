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

import org.jboss.resteasy.spi.ApplicationException;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.HttpResponse;
import org.jboss.resteasy.spi.PropertyInjector;

import javax.enterprise.inject.spi.BeanManager;
import javax.ws.rs.WebApplicationException;
import java.lang.reflect.Type;
import java.util.Map;

public class AppBaseWeldCdiPropertyInjector implements PropertyInjector {
	private PropertyInjector delegate;
	private Class<?> clazz;
	private boolean injectorEnabled = true;

	public AppBaseWeldCdiPropertyInjector(PropertyInjector delegate, Class<?> clazz,
			Map<Class<?>, Type> sessionBeanInterface, BeanManager manager) {
		this.delegate = delegate;
		this.clazz = clazz;

		if (sessionBeanInterface.containsKey(clazz)) {
			injectorEnabled = false;
		}
		if (!manager.getBeans(clazz).isEmpty() && UtilityMethods.isJaxrsComponent(clazz)) {
			injectorEnabled = false;
		}
	}

	@Override
	public void inject(Object target) {
		if (injectorEnabled) {
			delegate.inject(target);
		}
	}

	@Override
	public void inject(HttpRequest request, HttpResponse response, Object target)
			throws Failure, WebApplicationException, ApplicationException {
		if (injectorEnabled) {
			delegate.inject(request, response, target);
		}
	}

	@Override
	public String toString() {
		return "AppBaseWeldCdiPropertyInjector [delegate=" + delegate + ", clazz=" + clazz + ", injectorEnabled="
				+ injectorEnabled + "]";
	}

}
