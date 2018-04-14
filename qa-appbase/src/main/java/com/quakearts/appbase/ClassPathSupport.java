package com.quakearts.appbase;

import javax.enterprise.inject.Vetoed;

import com.quakearts.appbase.impl.ClasspathResourcesImpl;

@Vetoed
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
