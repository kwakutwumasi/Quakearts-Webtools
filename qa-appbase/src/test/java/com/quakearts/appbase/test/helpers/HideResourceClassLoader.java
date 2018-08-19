package com.quakearts.appbase.test.helpers;

import java.net.URL;

public class HideResourceClassLoader extends ClassLoader {
	private String resourcePattern;
	
	public String getResourcePattern() {
		return resourcePattern;
	}

	public void setResourcePattern(String resourcePattern) {
		this.resourcePattern = resourcePattern;
	}

	@Override
	public URL getResource(String name) {
		return resourcePattern!=null 
				&& name.matches(resourcePattern)?null:super.getResource(name);
	}
}
