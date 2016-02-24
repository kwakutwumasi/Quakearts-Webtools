package com.quakearts.webapp.logging;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class WebLogSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		Object listenerObject;
		if((listenerObject=event.getSession().getAttribute("com.quakearts.webapp.logging.LISTENER")) != null)
			((WebListener) listenerObject).close();
	}
}
