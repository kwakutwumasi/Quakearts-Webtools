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
package com.quakearts.tools.test.generator.bean.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.quakearts.tools.test.generator.exception.GeneratorException;

public final class CollectionFactoryFinder {

	private static final Logger log = Logger.getLogger(CollectionFactoryFinder.class.getName());
	
	private CollectionFactoryFinder() {
	}
	
	private static CollectionFactoryFinder instance = new CollectionFactoryFinder();
	private static Map<Class<?>, CollectionFactory> map = new HashMap<>();
	
	public static CollectionFactoryFinder getInstance() {
		return instance;
	}
	
	public CollectionFactory findCollectionFactory(Class<?> collectionClass){
		if(! map.containsKey(collectionClass)){
			throw new GeneratorException(collectionClass.getName()+" does have a Factory ");
		}

		return map.get(collectionClass);
	}
	
	public void registerCollectionFinder(Class<?> collectionClass, CollectionFactory factory){
		if(! map.containsKey(collectionClass)){
			ensureMatches(collectionClass, factory);
			map.put(collectionClass, factory);
		} else {
			log.warning("Duplicate factory attempted registration: "+factory.getClass().getName()+" for "+collectionClass);
		}
	}

	private void ensureMatches(Class<?> collectionClass, CollectionFactory factory) {
		try {
			Class<?> producedClass = factory.getClass().getMethod("createNewCollection").getReturnType();
			if(!collectionClass.isAssignableFrom(producedClass))
				throw new GeneratorException(factory.getClass().getName()+" does not produce an assignable class for "+collectionClass.getName());
		
		} catch (NoSuchMethodException | SecurityException e) {
			throw new GeneratorException("Unable to find method 'createNewCollection' from class "+factory.getClass().getName(), e);
		}
	}
	
}
