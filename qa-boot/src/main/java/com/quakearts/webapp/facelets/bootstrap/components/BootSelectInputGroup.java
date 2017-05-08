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
package com.quakearts.webapp.facelets.bootstrap.components;

import javax.faces.context.FacesContext;

import com.quakearts.webapp.facelets.bootstrap.behaviour.AutoCompleteBehavior;

public class BootSelectInputGroup extends BootSelectOneMenu {
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.selectinputgroup";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.selectinputgroup.renderer";

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	@Override
	public String getRendererType() {
		return RENDERER_TYPE;
	}

	@Override
	protected void validateValue(FacesContext context, Object value) {
		if(context.getAttributes().containsKey(AutoCompleteBehavior.SUGGESTIONPRESENT)){
			return;
		}
		
		super.validateValue(context, value);
	}
	
}
