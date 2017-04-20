package com.quakearts.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@ApplicationPath("test")
public class TestApplication extends Application {
	@Override
	public Set<Object> getSingletons() {
		return new HashSet<>(Arrays.asList(new Object(){
			@Path("hello")
			@Produces(MediaType.APPLICATION_JSON)
			public String sayHello(){
				return "{\"message\":\"Hello!\"}";
			}
		}));
	}
}
