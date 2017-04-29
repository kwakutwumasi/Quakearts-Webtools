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
