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

import com.quakearts.classannotationscanner.listener.ClassAnnotationScanningListener;
import com.quakearts.tools.test.generator.annotation.CollectionFactoryFor;
import com.quakearts.tools.test.generator.bean.collections.CollectionFactory;
import com.quakearts.tools.test.generator.bean.collections.CollectionFactoryFinder;
import com.quakearts.tools.test.generator.exception.GeneratorException;

public final class CollectionFactoryAnnotationListener implements ClassAnnotationScanningListener {

	@Override
	public String[] getAnnotationsToListenFor() {
		return new String[]{CollectionFactoryFor.class.getName()};
	}

	@Override
	public void handle(String className, String annotation) {
		try {
			Class<?> factoryClass = Class.forName(className);
			
			if(CollectionFactory.class.isAssignableFrom(factoryClass)){
				CollectionFactory factory = (CollectionFactory) factoryClass.newInstance();
				
				CollectionFactoryFor factoryAnnotation = factoryClass.getAnnotation(CollectionFactoryFor.class);
				for(Class<?> collectionClass:factoryAnnotation.value())
					CollectionFactoryFinder.getInstance().registerCollectionFinder(collectionClass, factory);
			
			} else {
				throw new GeneratorException("Factory class "+className+" does not implement "
						+com.quakearts.tools.test.generator.bean.collections.CollectionFactory.class.getName());
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new GeneratorException("Factory class "+className+" cannot be loaded.");
		}
	}

}
