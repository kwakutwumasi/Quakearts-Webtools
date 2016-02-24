package com.quakearts.webapp.logging.finder.impl;

import java.util.Enumeration;
import java.util.logging.Handler;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;

import org.jboss.logmanager.LogManager;

import com.quakearts.webapp.logging.WebListenerRegistrar;
import com.quakearts.webapp.logging.finder.RegistrarFinder;

public abstract class AbstractHandlerFinder implements RegistrarFinder {

	@Override
	public WebListenerRegistrar locateWebListenerRegistrar(ServletConfig config)
			throws Exception {
		Logger logger = LogManager.getLogManager().getLogger("");
		WebListenerRegistrar registrar = findFromHandlers(logger.getHandlers());
		if(registrar==null){
			Enumeration<String> names=LogManager.getLogManager().getLoggerNames();
			while (names.hasMoreElements()) {
				logger= LogManager.getLogManager().getLogger((String) names.nextElement());
				registrar = findFromHandlers(logger.getHandlers());
				if(registrar!=null)
					return registrar;
			}
			throw new IllegalStateException("Cannot find log handler of type "+WebListenerRegistrar.class.getName());
		}else
			return registrar;
	}
	
	abstract protected WebListenerRegistrar findFromHandlers(Handler[] handlers);
}
