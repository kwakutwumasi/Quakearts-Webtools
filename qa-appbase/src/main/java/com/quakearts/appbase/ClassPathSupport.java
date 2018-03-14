package com.quakearts.appbase;

import com.quakearts.appbase.impl.ClasspathResourcesImpl;

public class ClassPathSupport {
	private static ClasspathResources classpathResources;

	private ClassPathSupport() {}
	
	public static ClasspathResources getClasspathResources() {
		if(classpathResources == null) {
			classpathResources = new ClasspathResourcesImpl();
			classpathResources.loadClassPath();
		}
		return classpathResources;
	}
}
