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
package com.quakearts.tools.test.generator.factory;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.quakearts.tools.test.generator.BeanGenerator;
import com.quakearts.tools.test.generator.Generator;
import com.quakearts.tools.test.generator.bean.NoObjectGenerator;
import com.quakearts.tools.test.generator.exception.GeneratorException;
import com.quakearts.tools.test.generator.primitives.EnumRandomGenerator;

public final class GeneratorFactory {

	private GeneratorFactory() {
	}

	private static final Map<Class<?>, Generator<?>> MAP = new ConcurrentHashMap<>();
	
	private static GeneratorFactory instance = new GeneratorFactory();
	
	public static GeneratorFactory getInstance() {
		return instance;
	}
		
	public GeneratorFactory addGenerator(Generator<?> generator,
			Class<?>...clazzes){
		for(Class<?> clazz:clazzes){
			if(MAP.containsKey(clazz))
				throw new GeneratorException("Generator already registered for class "+clazz.getName());
				
			MAP.put(clazz, generator);
		}
		return this;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> Generator<T> getGenerator(Class<T> clazz){
		Generator<T> generator;
		if(MAP.containsKey(clazz)){
			generator = (Generator<T>) MAP.get(clazz);
		} else {
			if(!isForbiddenType(clazz)){
				generator = new BeanGenerator<>(clazz);
				MAP.put(clazz, generator);
				((BeanGenerator<T>)generator).init();
			} else if(clazz.isEnum()) {
				generator = new EnumRandomGenerator(clazz);
			} else {
				generator = new NoObjectGenerator<>();
			}
		}
		
		return generator;
	}
	
	private boolean isForbiddenType(Class<?> clazz){
		return clazz.isArray() 
				|| Collection.class.isAssignableFrom(clazz)
				|| clazz.isInterface()
				|| clazz.isAnnotation()
				|| clazz.isPrimitive()
				|| clazz.isEnum();
	}
	
	public <T> T generate(Class<T> clazz){
		return getGenerator(clazz).generateRandom();
	}
	
	
}
