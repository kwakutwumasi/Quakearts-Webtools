package com.quakearts.webtools.resteasy.cdi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.ProcessInjectionTarget;
import javax.enterprise.inject.spi.WithAnnotations;
import javax.enterprise.util.AnnotationLiteral;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.Provider;

public class AppBaseResteasyCdiExtension implements Extension {

	private BeanManager beanManager;

	private final List<Class<?>> providers = new ArrayList<>();
	private final List<Class<?>> resources = new ArrayList<>();

	public static final Annotation requestScopedLiteral = new AnnotationLiteral<RequestScoped>() {
		private static final long serialVersionUID = 3381824686081435817L;
	};
	public static final Annotation applicationScopedLiteral = new AnnotationLiteral<ApplicationScoped>() {
		private static final long serialVersionUID = -8211157243671012820L;
	};

	private Map<Class<?>, Type> sessionBeanInterface = new HashMap<Class<?>, Type>();

	public void observeBeforeBeanDiscovery(@Observes BeforeBeanDiscovery event, BeanManager beanManager) {
		setBeanManager(beanManager);
	}

	public <T> void observeResources(@WithAnnotations({ Path.class }) @Observes ProcessAnnotatedType<T> event,
			BeanManager beanManager) {
		setBeanManager(beanManager);
	}

	public <T> void observeProviders(@WithAnnotations({ Provider.class }) @Observes ProcessAnnotatedType<T> event,
			BeanManager beanManager) {
		setBeanManager(beanManager);
	}

	public <T extends Application> void observeApplications(@Observes ProcessAnnotatedType<T> event,
			BeanManager beanManager) {
		setBeanManager(beanManager);
		event.setAnnotatedType(wrapAnnotatedType(event.getAnnotatedType(), applicationScopedLiteral));
	}

	protected <T> AnnotatedType<T> wrapAnnotatedType(AnnotatedType<T> type, Annotation scope) {
		if (UtilityMethods.isScopeDefined(type, beanManager)) {
			return type;
		} else {
			return new AppBaseReasteasyJaxrsAnnotatedType<T>(type, scope);
		}
	}

	public <T> void observeInjectionTarget(@Observes ProcessInjectionTarget<T> event) {
		if (UtilityMethods.isJaxrsComponent(event.getAnnotatedType().getJavaClass())) {
			event.setInjectionTarget(wrapInjectionTarget(event));
		}
	}

	protected <T> InjectionTarget<T> wrapInjectionTarget(ProcessInjectionTarget<T> event) {
		return new AppBaseReasteasyJaxrsInjectionTarget<T>(event.getInjectionTarget(),
				event.getAnnotatedType().getJavaClass());
	}

	public Map<Class<?>, Type> getSessionBeanInterface() {
		return sessionBeanInterface;
	}

	private void setBeanManager(BeanManager beanManager) {
		if (this.beanManager == null) {
			this.beanManager = beanManager;
		}
	}

	public List<Class<?>> getProviders() {
		return providers;
	}

	public List<Class<?>> getResources() {
		return resources;
	}
}
