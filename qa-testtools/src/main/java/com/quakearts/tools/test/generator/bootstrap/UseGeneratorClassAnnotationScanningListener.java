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
package com.quakearts.tools.test.generator.bootstrap;

import java.lang.reflect.Method;

import com.quakearts.classannotationscanner.listener.ClassAnnotationScanningListener;
import com.quakearts.tools.test.generator.Generator;
import com.quakearts.tools.test.generator.annotation.UseGenerator;
import com.quakearts.tools.test.generator.exception.GeneratorException;
import com.quakearts.tools.test.generator.factory.GeneratorFactory;

/**Class for scanning and registering generators for annotated types
 * @author kwakutwumasi-afriyie
 *
 */
public final class UseGeneratorClassAnnotationScanningListener implements ClassAnnotationScanningListener {

	@Override
	public String[] getAnnotationsToListenFor() {
		return new String[]{UseGenerator.class.getName()};
	}

	@Override
	public void handle(String className, String annotation) {
		try {
			Class<?> generatedClass = Class.forName(className);
			UseGenerator generatorAnnotation = generatedClass.getAnnotation(UseGenerator.class);
			Class<? extends Generator<?>> generatorClass = generatorAnnotation.value();
			
			Method method = generatorClass.getMethod("generateRandom");
			
			if(!generatorClass.getName().startsWith("com.quakearts.tools.test.primitives")
				&& !generatedClass.isAssignableFrom(method.getReturnType())){
				throw new GeneratorException("Mismatched generator: "+className+" "
						+ "does not generate an assignable form of "
						+ generatedClass.getName());
			}
			
			GeneratorFactory.getInstance().addGenerator((Generator<?>)generatorClass.newInstance(), generatedClass);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new GeneratorException("Unable to load class "+className);
		} catch (ClassCastException e) {
			throw new GeneratorException("Class "+className+" does not implement com.quakearts.tools.test.Generator<?>");
		} catch (NoSuchMethodException | SecurityException e) {
			throw new GeneratorException("Class "+className+" does not have an accessible method 'public T generateRandom()'");
		}
	}

}
