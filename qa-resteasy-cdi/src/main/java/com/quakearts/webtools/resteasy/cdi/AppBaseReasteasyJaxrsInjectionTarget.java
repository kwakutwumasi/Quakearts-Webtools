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

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;

import org.jboss.resteasy.core.PropertyInjectorImpl;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.HttpResponse;
import org.jboss.resteasy.spi.PropertyInjector;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.resteasy.spi.validation.GeneralValidatorCDI;
import org.jboss.resteasy.util.GetRestful;

public class AppBaseReasteasyJaxrsInjectionTarget<T> implements InjectionTarget<T> {

	private InjectionTarget<T> delegate;
	private Class<T> clazz;
	private PropertyInjector propertyInjector;
	private GeneralValidatorCDI validator;

	public AppBaseReasteasyJaxrsInjectionTarget(InjectionTarget<T> delegate, Class<T> clazz) {
		this.delegate = delegate;
		this.clazz = clazz;
	}

	public void inject(T instance, CreationalContext<T> ctx) {
		delegate.inject(instance, ctx);

		if (propertyInjector == null) {
			propertyInjector = getPropertyInjector();
		}

		HttpRequest request = ResteasyProviderFactory.getContextData(HttpRequest.class);
		HttpResponse response = ResteasyProviderFactory.getContextData(HttpResponse.class);

		if ((request != null) && (response != null)) {
			propertyInjector.inject(request, response, instance);
		} else {
			propertyInjector.inject(instance);
		}

		if (request != null) {
			validate(request, instance);
		}
	}

	public void postConstruct(T instance) {
		delegate.postConstruct(instance);
	}

	public void preDestroy(T instance) {
		delegate.preDestroy(instance);
	}

	public void dispose(T instance) {
		delegate.dispose(instance);
	}

	public Set<InjectionPoint> getInjectionPoints() {
		return delegate.getInjectionPoints();
	}

	public T produce(CreationalContext<T> ctx) {
		return delegate.produce(ctx);
	}

	private PropertyInjector getPropertyInjector() {
		return new PropertyInjectorImpl(clazz, ResteasyProviderFactory.getInstance());
	}

	private void validate(HttpRequest request, T instance) {
		if (GetRestful.isRootResource(clazz)) {
			ResteasyProviderFactory providerFactory = ResteasyProviderFactory.getInstance();
			ContextResolver<GeneralValidatorCDI> resolver = providerFactory
					.getContextResolver(GeneralValidatorCDI.class, MediaType.WILDCARD_TYPE);
			if (resolver != null) {
				validator = resolver.getContext(null);
			}
			if (validator != null && validator.isValidatableFromCDI(clazz)) {
				validator.validate(request, instance);
				validator.checkViolationsfromCDI(request);
			}
		}
	}
}
