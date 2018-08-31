package com.quakearts.webapp.security.rest.util;

import java.util.Iterator;
import java.util.ServiceLoader;

import com.quakearts.webapp.security.rest.exception.RestSecurityException;

public final class PluginService {
	private PluginService() {}
	
	public static <T> T loadPlugin(Class<T> pluginInterface) {
		Iterator<T> iterator = ServiceLoader
				.load(pluginInterface)
				.iterator();

		if(iterator.hasNext()) {
			T plugin = iterator.next();

			if(iterator.hasNext())
				throw new RestSecurityException("Multiple services registered as "
						+pluginInterface.getName()
						+" were found on the classpath. Please remove reduntant services");
			return plugin;
		}
		
		return null;
	}
}
