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

import com.quakearts.webapp.facelets.bootstrap.common.BootOnLoadComponent;
import com.quakearts.webapp.facelets.bootstrap.components.BootDateButton;

public class BootDateButtonHandler extends BootBaseHandler {
	public static final String DATE_SCRIPT_FUNCTION = "var dc_idVal = qab.dc('#idVal', '#idDiv');";

	public BootDateButtonHandler(ComponentConfig config) {
		super(config);
	}
	
	@Override
	public void onComponentCreated(FaceletContext ctx, final UIComponent component,
			UIComponent parent) {
		if(!(component instanceof BootDateButton))
			throw new AbortProcessingException("Component must be of type "+BootDateButton.class.getName());
		
		BootOnLoadComponent.addScriptContent(fctx->{
			String id = component.getClientId(fctx);
			String idJs = id.replace("-", "_");
			return "\n"+getContents(idJs, id)+"\n";
		}, ctx.getFacesContext());
	}
		
	private String getContents(String idJs, String id) {
		return DATE_SCRIPT_FUNCTION.replace("idVal", idJs)
				.replace("idDiv", id);
	}

}
