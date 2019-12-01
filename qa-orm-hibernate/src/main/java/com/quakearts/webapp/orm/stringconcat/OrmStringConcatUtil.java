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
package com.quakearts.webapp.orm.stringconcat;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**Utility method for concatenating String properties of JPA objects. This is to prevent
 * String concatenation exceptions thrown by the database when the string is to long to fit into a column
 * @author kwakutwumasi-afriyie
 *
 */
public class OrmStringConcatUtil {
	private static Map<Class<?>, OrmStringConcat> cache = new ConcurrentHashMap<>();
	private static Set<ConcatenationListener> concatenationListeners = new CopyOnWriteArraySet<>();
	
	private OrmStringConcatUtil() {}
	
	/**Trim string properties in the object
	 * @param object
	 */
	public static void trimStrings(Object object) {
		OrmStringConcat concat = cache.get(object.getClass());
		if(concat == null){
			concat = new OrmStringConcat(object.getClass());
			cache.put(object.getClass(), concat);
		}
		
		concat.trimStrings(object);
	}
	
	public static void addConcatenationListener(ConcatenationListener listener) {
		concatenationListeners.add(listener);
	}
	
	public static void removeConcatenationListener(ConcatenationListener listener) {
		concatenationListeners.remove(listener);
	}
	
	static void notify(Class<?> beanClass, Object instance, String value, String trimmed){
		concatenationListeners.stream().forEach(listener->listener.notify(beanClass, instance, value, trimmed));
	}
}
