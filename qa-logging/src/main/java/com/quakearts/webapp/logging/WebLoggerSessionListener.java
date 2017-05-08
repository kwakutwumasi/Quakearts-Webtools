/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.logging;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class WebLoggerSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent paramHttpSessionEvent) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		Object listenerObject;
		if((listenerObject=event.getSession().getAttribute("com.quakearts.webapp.logging.LISTENER")) != null){
			((WebListener) listenerObject).close();
		}
	}

}
