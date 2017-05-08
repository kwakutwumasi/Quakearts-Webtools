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
import java.util.concurrent.ConcurrentHashMap;

public class OrmStringConcatUtil {
	private static Map<Class<?>, OrmStringConcat> cache = new ConcurrentHashMap<>();
	
	private OrmStringConcatUtil() {
	}
	
	public static void trimStrings(Object object) {
		OrmStringConcat concat = cache.get(object.getClass());
		if(concat == null){
			concat = new OrmStringConcat(object.getClass());
			cache.put(object.getClass(), concat);
		}
		
		concat.trimStrings(object);
	}
}
