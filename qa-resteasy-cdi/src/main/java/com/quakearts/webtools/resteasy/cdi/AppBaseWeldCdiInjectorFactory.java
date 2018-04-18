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

import org.jboss.resteasy.core.InjectorFactoryImpl;
import org.jboss.resteasy.core.ValueInjector;
import org.jboss.resteasy.spi.ConstructorInjector;
import org.jboss.resteasy.spi.InjectorFactory;
import org.jboss.resteasy.spi.MethodInjector;
import org.jboss.resteasy.spi.PropertyInjector;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.resteasy.spi.metadata.Parameter;
import org.jboss.resteasy.spi.metadata.ResourceClass;
import org.jboss.resteasy.spi.metadata.ResourceConstructor;
import org.jboss.resteasy.spi.metadata.ResourceLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.appbase.exception.ConfigurationException;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("rawtypes")
public class AppBaseWeldCdiInjectorFactory implements InjectorFactory {
	private BeanManager manager;
	private InjectorFactory delegate = new InjectorFactoryImpl();
	private AppBaseResteasyCdiExtension extension;
	private Map<Class<?>, Type> sessionBeanInterface;
	private static final Logger log = LoggerFactory.getLogger(AppBaseWeldCdiInjectorFactory.class);

	public AppBaseWeldCdiInjectorFactory() {
		this.manager = lookupBeanManager();
		this.extension = lookupResteasyCdiExtension();
		sessionBeanInterface = extension.getSessionBeanInterface();
	}

	@Override
	public ValueInjector createParameterExtractor(Parameter parameter, ResteasyProviderFactory providerFactory) {
		return delegate.createParameterExtractor(parameter, providerFactory);
	}

	@Override
	public MethodInjector createMethodInjector(ResourceLocator method, ResteasyProviderFactory factory) {
		return delegate.createMethodInjector(method, factory);
	}

	@Override
	public PropertyInjector createPropertyInjector(ResourceClass resourceClass,
			ResteasyProviderFactory providerFactory) {
		return new AppBaseWeldCdiPropertyInjector(delegate.createPropertyInjector(resourceClass, providerFactory),
				resourceClass.getClazz(), sessionBeanInterface, manager);
	}

	@Override
	public ConstructorInjector createConstructor(ResourceConstructor constructor,
			ResteasyProviderFactory providerFactory) {
		Class<?> clazz = constructor.getConstructor().getDeclaringClass();

		ConstructorInjector injector = cdiConstructor(clazz);
		if (injector != null)
			return injector;
		log.debug("No CDI Beans found for " + clazz);
		return delegate.createConstructor(constructor, providerFactory);
	}

	@Override
	public ConstructorInjector createConstructor(Constructor constructor, ResteasyProviderFactory factory) {
		Class<?> clazz = constructor.getDeclaringClass();

		ConstructorInjector injector = cdiConstructor(clazz);
		if (injector != null)
			return injector;
		log.debug("No CDI Beans found for " + clazz);
		return delegate.createConstructor(constructor, factory);
	}

	protected ConstructorInjector cdiConstructor(Class<?> clazz) {
		if (!manager.getBeans(clazz).isEmpty()) {
			return new AppBaseWeldCdiConstructorInjector(clazz, manager);
		}

		if (sessionBeanInterface.containsKey(clazz)) {
			Type intfc = sessionBeanInterface.get(clazz);
			return new AppBaseWeldCdiConstructorInjector(intfc, manager);
		}

		return null;
	}

	public PropertyInjector createPropertyInjector(Class resourceClass, ResteasyProviderFactory factory) {
		return new AppBaseWeldCdiPropertyInjector(delegate.createPropertyInjector(resourceClass, factory),
				resourceClass, sessionBeanInterface, manager);
	}

	public ValueInjector createParameterExtractor(Class injectTargetClass, AccessibleObject injectTarget, Class type,
			Type genericType, Annotation[] annotations, ResteasyProviderFactory factory) {
		return delegate.createParameterExtractor(injectTargetClass, injectTarget, type, genericType, annotations,
				factory);
	}

	public ValueInjector createParameterExtractor(Class injectTargetClass, AccessibleObject injectTarget, Class type,
			Type genericType, Annotation[] annotations, boolean useDefault, ResteasyProviderFactory factory) {
		return delegate.createParameterExtractor(injectTargetClass, injectTarget, type, genericType, annotations,
				useDefault, factory);
	}

	/**
	 * Do a lookup for BeanManager instance. JNDI and ServletContext is searched.
	 *
	 * @return BeanManager instance
	 */
	protected BeanManager lookupBeanManager() {
		BeanManager beanManager = null;
		beanManager = CDI.current().getBeanManager();
		if (beanManager != null) {
			return beanManager;
		}

		throw new RuntimeException("Unable to lookup bean manager");
	}

	private AppBaseResteasyCdiExtension lookupResteasyCdiExtension() {
		Set<Bean<?>> beans = manager.getBeans(AppBaseResteasyCdiExtension.class);
		Bean<?> bean = manager.resolve(beans);
		if (bean == null) {
			throw new ConfigurationException("Cannot get " + AppBaseResteasyCdiExtension.class.getName());
		}
		CreationalContext<?> context = manager.createCreationalContext(bean);
		return (AppBaseResteasyCdiExtension) manager.getReference(bean, AppBaseResteasyCdiExtension.class, context);
	}
}
