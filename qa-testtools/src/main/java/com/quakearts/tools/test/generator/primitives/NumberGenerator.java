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
package com.quakearts.tools.test.generator.primitives;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.quakearts.tools.test.generator.Generator;
import com.quakearts.tools.test.generator.GeneratorBase;
import com.quakearts.tools.test.generator.exception.GeneratorException;
import com.quakearts.tools.test.generator.primitives.configuration.AnnotationPropertyConsumer;
import com.quakearts.tools.test.generator.primitives.configuration.GenerateWith;

/**
 * @author QuakesHome
 *
 * @param <T>
 */
public abstract class NumberGenerator<T extends Number> extends GeneratorBase<T> implements AnnotationPropertyConsumer {

	private static Properties properties = new Properties();

	static {
		InputStream in = DateGenerator.class.getClassLoader().getResourceAsStream("random.numbers");
		if (in != null) {
			try {
				properties.load(in);
			} catch (IOException e) {
				throw new RuntimeException("Unable to load random.numbers");
			}
		}
	}
	
	@Override
	public Generator<?> configureFromAnnotations(GenerateWith generatorProperty) {
		if(generatorProperty.max()>0)
			max = getFromDouble(generatorProperty.max());

		if(generatorProperty.min()>0)
			min = getFromDouble(generatorProperty.min());
			
		return this;
	}
	
	protected T max, min;
	public NumberGenerator<T> max(T max){
		this.max = max;	
		
		if(min!=null && !isValid(max, max))
			max=min;
		
		return this;
	}

	public NumberGenerator<T> min(T min){
		this.min = min;	

		if(max!=null && !isValid(max, min))
			max=min;
		
		return this;
	}

	public NumberGenerator(T max, T min) {
		this.max = max;
		this.min = min;
	}
	
	public NumberGenerator() {
	}
	
	@Override
	public NumberGenerator<T> useField(String fieldName) {
		try {
			if(properties.containsKey(fieldName+".max"))
				max = getFromString(properties.getProperty(fieldName+".max"));			
		} catch (NumberFormatException e) {
			throw new GeneratorException("Invalid number for "+fieldName+".max: "+properties.getProperty(fieldName+".max"), e);
		}

		try {
			if(properties.containsKey(fieldName+".min"))
				min = getFromString(properties.getProperty(fieldName+".min"));
		} catch (NumberFormatException e) {
			throw new GeneratorException("Invalid number for "+fieldName+".min: "+properties.getProperty(fieldName+".max"), e);
		}

		return this;
	}
	
	protected abstract T getFromString(String string);
	protected abstract T getFromDouble(Double number);
	protected abstract boolean isValid(T max, T min);
}
