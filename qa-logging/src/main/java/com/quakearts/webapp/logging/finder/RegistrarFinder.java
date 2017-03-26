package com.quakearts.webapp.logging.finder;

import javax.servlet.ServletConfig;

import com.quakearts.webapp.logging.WebListenerRegistrar;

public interface RegistrarFinder {
	public WebListenerRegistrar locateWebListenerRegistrar(ServletConfig config) throws Exception;
}
