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
package com.quakearts.webapp.facelets.bootstrap.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PreRenderViewEvent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.FaceletContext;

import com.quakearts.webapp.facelets.bootstrap.components.BootPagination;
import com.quakearts.webapp.facelets.bootstrap.listeners.BootPaginationListener;

public class BootPaginationHandler extends BootBaseHandler {

	private static final BootPaginationListener PAGINATION_LISTENER = new BootPaginationListener();
	
	public BootPaginationHandler(ComponentConfig config) {
		super(config);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onComponentCreated(FaceletContext ctx, UIComponent c,
			UIComponent parent) {
		if(!(c instanceof BootPagination))
			throw new AbortProcessingException("Component must be of type "+BootPagination.class.getName());
				
		List<BootPagination> paginations = (List<BootPagination>) ctx.getFacesContext().getAttributes().get(BootPagination.PAGINATION_KEY);
		if(paginations==null){ //Do one time actions. Create pagination array, subscribe to event
			paginations = new ArrayList<BootPagination>();
			ctx.getFacesContext().getAttributes().put(BootPagination.PAGINATION_KEY,paginations);
			ctx.getFacesContext().getViewRoot().subscribeToEvent(PreRenderViewEvent.class, PAGINATION_LISTENER);
		}
		paginations.add((BootPagination)c);
	}
}
