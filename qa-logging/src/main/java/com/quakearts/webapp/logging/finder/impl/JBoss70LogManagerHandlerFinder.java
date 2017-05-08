/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.logging.finder.impl;

import java.util.logging.Handler;
import com.quakearts.webapp.logging.WebListenerRegistrar;

public class JBoss70LogManagerHandlerFinder extends AbstractHandlerFinder {
	
	protected WebListenerRegistrar findFromHandlers(Handler[] handlers){
		for(Handler handler:handlers){
			if(handler instanceof WebListenerRegistrar)
				return (WebListenerRegistrar) handler;
		}
		return null;
	}
}
