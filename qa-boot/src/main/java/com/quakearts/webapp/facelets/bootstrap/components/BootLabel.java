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
package com.quakearts.webapp.facelets.bootstrap.components;

import java.util.HashMap;

import javax.el.ValueExpression;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootLabel extends UIOutput {
	private ValueExpression typeExpression;
	
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.label";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.label.renderer";

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	public ValueExpression getTypeExpression() {
		return typeExpression;
	}
	
	public void setTypeExpression(ValueExpression typeExpression) {
		this.typeExpression = typeExpression;
	}
	
	@Override
	public Object saveState(FacesContext context) {
		return  new Object[]{typeExpression, super.saveState(context)};
	}
	
	@Override
	public void restoreState(FacesContext context, Object state) {
		typeExpression = (ValueExpression)((Object[])state)[0];
		super.restoreState(context, ((Object[])state)[1]);
	}
	
	private static final HashMap<String, String> VALID_TYPES = new HashMap<String, String>();
	static{
		VALID_TYPES.put("default", "");
		VALID_TYPES.put("primary", "");
		VALID_TYPES.put("success", "");
		VALID_TYPES.put("info", "");
		VALID_TYPES.put("warning", "");
		VALID_TYPES.put("danger", "");
	}
	
	public boolean isValid(String type){
		return VALID_TYPES.get(type)!=null;
	}

	@Override
	public String getRendererType() {
		return RENDERER_TYPE;
	}

	@Override
	public void setRendererType(String rendererType) {
	}
	
	public String get(String attribute) {
		String attributeValue = ObjectExtractor
				.extractString(getValueExpression(attribute), getFacesContext()
						.getELContext());
		if (attributeValue == null)
			attributeValue = (String) getAttributes().get(attribute);

		if("type".equals(attribute)){
			if(!isValid(attributeValue))
				attributeValue = "default";
		}
		
		return attributeValue;
	}
}
