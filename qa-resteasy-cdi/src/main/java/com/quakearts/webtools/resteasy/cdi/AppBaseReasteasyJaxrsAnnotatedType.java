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

import javax.enterprise.inject.spi.AnnotatedConstructor;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AppBaseReasteasyJaxrsAnnotatedType<TYPE> implements AnnotatedType<TYPE> {

	private AnnotatedType<TYPE> delegate;
	private Set<Annotation> annotations = new HashSet<Annotation>();

	public AppBaseReasteasyJaxrsAnnotatedType(AnnotatedType<TYPE> delegate, Annotation scope) {
		this.delegate = delegate;
		this.annotations.addAll(delegate.getAnnotations());
		this.annotations.add(scope);
	}

	public Set<AnnotatedConstructor<TYPE>> getConstructors() {
		return delegate.getConstructors();
	}

	public Set<AnnotatedField<? super TYPE>> getFields() {
		return delegate.getFields();
	}

	public Class<TYPE> getJavaClass() {
		return delegate.getJavaClass();
	}

	public Set<AnnotatedMethod<? super TYPE>> getMethods() {
		return delegate.getMethods();
	}

	public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
		return delegate.getAnnotation(annotationType);
	}

	public Set<Annotation> getAnnotations() {
		return Collections.unmodifiableSet(annotations);
	}

	public Type getBaseType() {
		return delegate.getBaseType();
	}

	public Set<Type> getTypeClosure() {
		return delegate.getTypeClosure();
	}

	public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
		return delegate.isAnnotationPresent(annotationType);
	}
}
