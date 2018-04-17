package com.quakearts.webtools.resteasy.validation.test.helpers;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("rest")
public class TestApplication extends Application {
	
	Set<Class<?>> singletons = new HashSet<>();
	
	@Override
	public Set<Class<?>> getClasses() {
		singletons.add(TestResource.class);
		singletons.add(TestProvider.class);
		singletons.add(TestConstraintExceptionMapper.class);
		return singletons;
	}	
}
