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
import org.jboss.resteasy.spi.ConstructorInjector;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.ws.rs.WebApplicationException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AppBaseWeldCdiConstructorInjector implements ConstructorInjector {
	private BeanManager manager;
	private Type type;
	private static final Logger log = LoggerFactory.getLogger(AppBaseWeldCdiConstructorInjector.class);

	public AppBaseWeldCdiConstructorInjector(Type type, BeanManager manager) {
		this.type = type;
		this.manager = manager;
	}

	public Object construct() {
		Set<Bean<?>> beans = manager.getBeans(type);

		if (beans.size() > 1) {
			Set<Bean<?>> modifiableBeans = new HashSet<Bean<?>>();
			modifiableBeans.addAll(beans);
			for (Iterator<Bean<?>> iterator = modifiableBeans.iterator(); iterator.hasNext();) {
				Bean<?> bean = iterator.next();
				if (!bean.getBeanClass().equals(type) && !bean.isAlternative()) {
					iterator.remove();
				}
			}
			beans = modifiableBeans;
		}

		if (log.isDebugEnabled()) {
			log.debug("Beans found:" + type + ":" + beans);
		}

		Bean<?> bean = manager.resolve(beans);
		CreationalContext<?> context = manager.createCreationalContext(bean);
		return manager.getReference(bean, type, context);
	}

	public Object construct(HttpRequest request, HttpResponse response)
			throws Failure, WebApplicationException, ApplicationException {
		return construct();
	}

	public Object[] injectableArguments() {
		return new Object[0];
	}

	public Object[] injectableArguments(HttpRequest request, HttpResponse response) throws Failure {
		return injectableArguments();
	}

	@Override
	public String toString() {
		return "AppBaseWeldCdiConstructorInjector [manager=" + manager.getClass() + ", type=" + type.getTypeName()
				+ "]";
	}

}
