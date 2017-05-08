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

public class BootFileInputHandler extends BootBaseHandler {

	public BootFileInputHandler(ComponentConfig config) {
		super(config);
	}

	@Override
	public void onComponentCreated(FaceletContext ctx, UIComponent c,
			UIComponent parent) {
		
		if(c.getValueExpression("value")==null && c.getValueExpression("data")==null)
			throw new AbortProcessingException("One of attribtes 'data' or 'value' is required and must be a ValueExpression");
		
		if(c.getValueExpression("accept")==null && c.getAttributes().get("accept")==null)
			throw new AbortProcessingException("Attribte 'accept' is required");
	}
}
