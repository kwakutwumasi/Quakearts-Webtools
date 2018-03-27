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

import javax.faces.component.UIComponent;
import javax.faces.event.AbortProcessingException;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.FaceletContext;

import com.quakearts.webapp.facelets.bootstrap.components.BootAjaxLoaderComponent;

public class BootAjaxLoaderHandler extends BootBaseHandler {	
	public BootAjaxLoaderHandler(ComponentConfig config) {
		super(config);
	}
		
	@Override
	public void onComponentCreated(FaceletContext ctx, UIComponent component,
			UIComponent parent) {
		
		if(!(component instanceof BootAjaxLoaderComponent))
			throw new AbortProcessingException("Component must be of type "
					+ BootAjaxLoaderComponent.class.getName());
		
		if(component.getValueExpression("mainloaderimage")==null && component.getAttributes().get("mainloaderimage")==null)
			throw new AbortProcessingException("Attribute mainloaderimage is required");

		if(component.getValueExpression("miniloaderimage")==null && component.getAttributes().get("miniloaderimage")==null)
			throw new AbortProcessingException("Attribute miniloaderimage is required");
		
	}
	

}
