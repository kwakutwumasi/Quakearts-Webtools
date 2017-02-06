package com.quakearts.webapp.hibernate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HibernateStringConcatUtil {
	private static Map<Class<?>, HibernateStringConcat> cache = new ConcurrentHashMap<>();
	
	private HibernateStringConcatUtil() {
	}
	
	public static void trimStrings(Object object) {
		HibernateStringConcat concat = cache.get(object.getClass());
		if(concat == null){
			concat = new HibernateStringConcat(object.getClass());
			cache.put(object.getClass(), concat);
		}
		
		concat.trimStrings(object);
	}
}
