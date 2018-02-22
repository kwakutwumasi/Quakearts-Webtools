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
package com.quakearts.tools.test.generator;

import static com.quakearts.tools.test.generator.bootstrap.GeneratorBootstrap.excludes;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.quakearts.tools.test.generator.Generator;
import com.quakearts.tools.test.generator.GeneratorBase;
import com.quakearts.tools.test.generator.annotation.CollectionType;
import com.quakearts.tools.test.generator.annotation.NoGeneration;
import com.quakearts.tools.test.generator.annotation.Size;
import com.quakearts.tools.test.generator.annotation.UseGeneratorProperty;
import com.quakearts.tools.test.generator.bean.NoObjectGenerator;
import com.quakearts.tools.test.generator.bean.collections.CollectionFactory;
import com.quakearts.tools.test.generator.bean.collections.CollectionFactoryFinder;
import com.quakearts.tools.test.generator.bean.helper.Tuple;
import com.quakearts.tools.test.generator.bootstrap.GeneratorBootstrap;
import com.quakearts.tools.test.generator.exception.GeneratorException;
import com.quakearts.tools.test.generator.factory.GeneratorFactory;
import com.quakearts.tools.test.generator.primitives.configuration.AnnotationPropertyConsumer;
import com.quakearts.tools.test.generator.primitives.configuration.GenerateWith;

public final class BeanGenerator<T> extends GeneratorBase<T> {

	private Set<Tuple<PropertyDescriptor, Generator<?>>> generatorSet = new HashSet<>();
	private Map<PropertyDescriptor, MethodHandle> handleMap = new HashMap<>();
	private Map<PropertyDescriptor, MethodHandle> collectionHandleMap = new HashMap<>();
	private Class<?> beanClass;
	private Set<String> skipField = new HashSet<>();
	private Map<String, Generator<?>> replaceWith = new HashMap<>();

	static {
		GeneratorBootstrap.init();
	}

	public BeanGenerator(Class<T> beanClass) {
		this.beanClass = beanClass;
	}

	@Override
	public BeanGenerator<T> useField(String fieldName) {
		super.useField(fieldName);
		return this;
	}
	
	public BeanGenerator<T> init() {
		try {
			BeanInfo info = Introspector.getBeanInfo(beanClass);

			checkForNoArgConstructor();

			for (PropertyDescriptor descriptor : info.getPropertyDescriptors()) {
				if (excludes(descriptor.getName(), beanClass))
					continue;

				if(skipField.contains(descriptor.getName()))
					continue;

				if (descriptor.getReadMethod() != null && descriptor.getWriteMethod() != null) {
					// We have a bean property!
					
					NoGeneration noGeneration = getAnnotation(descriptor,NoGeneration.class);
					if(noGeneration!=null)
						continue;
					
					MethodHandle handle = getMethodHandle(descriptor.getWriteMethod());
					Class<?> propertyType = getPropertyType(descriptor);
					Generator<?> generator = getGenerator(descriptor.getName(), propertyType);
					if(!(generator instanceof BeanGenerator)){//Must always refer to the same type. Does not use 
						//per object settings, unlike primitive and perhaps custome generators
						UseGeneratorProperty setting = getAnnotation(descriptor, UseGeneratorProperty.class);
						if (setting != null) {
							generator = createNewGeneratorFromSetting(setting, generator);
						}

						GenerateWith properties = getAnnotation(descriptor, GenerateWith.class);
						if (properties != null && generator instanceof AnnotationPropertyConsumer) {
							generator = createNewGeneratorWithProperties(properties, generator);
						}						
					} 

					generatorSet.add(new Tuple<>(descriptor, generator));
					handleMap.put(descriptor, handle);
				}
			}
		} catch (IntrospectionException | IllegalAccessException e) {
			throw new GeneratorException(e);
		}

		return this;
	}

	private void checkForNoArgConstructor() {
		Constructor<?> noArgsConstructor = null;
		for (Constructor<?> constructor : beanClass.getConstructors()) {
			if (constructor.getParameterTypes().length == 0) {
				noArgsConstructor = constructor;
				break;
			}
		}

		if (noArgsConstructor == null)
			throw new GeneratorException("Bean class "+beanClass.getName()+" does not have a no argument constructor");
	}

	private MethodHandle getMethodHandle(Method method) throws IllegalAccessException {
		return new ConstantCallSite(MethodHandles.lookup().unreflect(method)).dynamicInvoker();
	}

	private Class<?> getPropertyType(PropertyDescriptor descriptor) {
		CollectionType collectionType = descriptor.getReadMethod().getAnnotation(CollectionType.class);

		Class<?> propertyType;
		if (collectionType == null) {
			propertyType = descriptor.getPropertyType();
		} else if (collectionType.value() != null) {// For Collections
			propertyType = collectionType.value();
		} else {
			throw new GeneratorException("Invalid " + CollectionType.class.getName() + " declaration at method "
					+ descriptor.getReadMethod() + " for " + beanClass.getName());
		}

		return propertyType;
	}

	private Generator<?> getGenerator(String fieldName, Class<?> propertyType) {
		if(replaceWith.containsKey(fieldName))
			return replaceWith.get(fieldName);
		
		return GeneratorFactory.getInstance().getGenerator(propertyType);
	}

	private <A extends Annotation> A getAnnotation(PropertyDescriptor descriptor, Class<A> annotationClass) {
		return descriptor.getReadMethod().getAnnotation(annotationClass);
	}

	private Generator<?> createNewGeneratorFromSetting(UseGeneratorProperty setting, Generator<?> generator)
			throws IllegalAccessException {
		try {
			return generator.getClass().newInstance().useField(setting.value());
		} catch (InstantiationException e) {
			throw new GeneratorException("Unable to instantiate new generator: " + generator.getClass().getName());
		}
	}

	private Generator<?> createNewGeneratorWithProperties(GenerateWith properties, Generator<?> generator)
			throws IllegalAccessException {
		try {
			return ((AnnotationPropertyConsumer) generator.getClass().newInstance())
					.configureFromAnnotations(properties);
		} catch (InstantiationException e) {
			throw new GeneratorException("Unable to instantiate new generator: " + generator.getClass().getName());
		}
	}
	
	public BeanGenerator<T> forField(String field){
		if(replacement == null)
			throw new GeneratorException("Call to #forField(String) must be preceeded by #use(Generator). Generator must also not be null");

		replaceWith.put(field, replacement);
		replacement = null;
		return this;
	}
	
	private Generator<?> replacement;
	
	public BeanGenerator<T> use(Generator<?> generator){
		replacement = generator;
		return this;
	}

	public BeanGenerator<T> doNotGenerate(String field){
		skipField.add(field);
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T generateRandom() {
		try {
			if(callStack.size()>getMaxCallDepth())//Prevent too many recursive calls to the singleton generator
				return null;
			
			callStack.push(this);
			T object = (T) beanClass.newInstance();
			populateFields(object);
			callStack.pop();
			return object;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new GeneratorException("Unable to instantiate new class: " + beanClass.getName());
		}
	}

	private void populateFields(T object) {
		for (Tuple<PropertyDescriptor, Generator<?>> tuple : generatorSet) {
			if(tuple.getSecond() instanceof NoObjectGenerator)
				continue;
			
			try {
				if (!tuple.getFirst().getPropertyType().isArray()
						&& !Collection.class.isAssignableFrom(tuple.getFirst().getPropertyType())) {
					handleMap.get(tuple.getFirst()).invoke(object, tuple.getSecond().generateRandom());
				} else {
					int sizeToGenerate = getSizeToGenerate(tuple);

					Collection<?> collection = tuple.getSecond().generateRandom(sizeToGenerate);
					if (tuple.getFirst().getPropertyType().isArray()) {
						handleMap.get(tuple.getFirst()).invoke(object, collection.toArray());
					} else {
						setCollection(tuple, object, collection, handleMap.get(tuple.getFirst()));
					}
				}
			} catch (Throwable e) {
				throw new GeneratorException(
						"Unable to set method " + tuple.getFirst().toString() + " for " + beanClass.getName(), e);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setCollection(Tuple<PropertyDescriptor, Generator<?>> tuple, T object, Collection collection,
			MethodHandle setterMethodhandle) {

		MethodHandle getterMethodHandle = collectionHandleMap.get(tuple.getFirst());
		if (getterMethodHandle == null) {
			try {
				getterMethodHandle = getMethodHandle(tuple.getFirst().getReadMethod());
			} catch (IllegalAccessException e) {
				throw new GeneratorException("Unable to pouplate " + beanClass.getName() + " with "
						+ tuple.getFirst().getPropertyType().getName(), e);
			}
			collectionHandleMap.put(tuple.getFirst(), getterMethodHandle);
		}

		Collection<?> objectCollection;

		try {
			objectCollection = (Collection<?>) getterMethodHandle.invoke(object);
		} catch (Throwable e) {
			throw new GeneratorException("Unable to pull class of type " + tuple.getFirst().getPropertyType().getName()
					+ " for " + beanClass.getName(), e);
		}

		if (objectCollection == null) {
			Class<? extends Collection<?>> collectionClass = (Class<? extends Collection<?>>) tuple.getFirst()
					.getPropertyType();

			CollectionFactory factory = CollectionFactoryFinder.getInstance().findCollectionFactory(collectionClass);
			objectCollection = factory.createNewCollection();
			try {
				setterMethodhandle.invoke(object, objectCollection);
			} catch (Throwable e) {
				throw new GeneratorException("Unable to pouplate " + beanClass.getName() + " with "
						+ tuple.getFirst().getPropertyType().getName(), e);
			}
		}
		
		objectCollection.addAll(collection);
	}

	private int getSizeToGenerate(Tuple<PropertyDescriptor, Generator<?>> tuple) {
		Size size = tuple.getFirst().getReadMethod().getAnnotation(Size.class);
		int sizeToGenerate;
		if (size != null) {
			if (size.value() != -1)
				sizeToGenerate = size.value();
			else if (size.max() != -1) {
				sizeToGenerate = random.nextInt(size.max());
				if (size.min() != -1)
					sizeToGenerate += size.min();
			} else {
				throw new GeneratorException(Size.class.getName() + " annotation for method: "
						+ tuple.getFirst().getReadMethod() + " is not valid. Missing one of value, max/min");
			}
		} else {
			sizeToGenerate = random.nextInt(10)+1;
		}

		return sizeToGenerate;
	}

}
