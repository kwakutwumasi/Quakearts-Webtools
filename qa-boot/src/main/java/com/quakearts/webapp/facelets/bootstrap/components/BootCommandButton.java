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

import java.util.ArrayList;

import javax.faces.component.html.HtmlCommandButton;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootCommandButton extends HtmlCommandButton {	
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.btn";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.btn.renderer";
		
	public BootCommandButton() {
		getAttributes().put("javax.faces.component.UIComponentBase.attributesThatAreSet", new ArrayList<String>());
	}
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
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

		if("displayType".equals(attribute)){
	    	if(attributeValue==null || (!attributeValue.equals("default"))
	        		&& (!attributeValue.equals("primary")) && (!attributeValue.equals("success"))
	        		&& (!attributeValue.equals("info")) && (!attributeValue.equals("warning"))
	        		&& (!attributeValue.equals("danger")))
	        		attributeValue = "default";
		}
		if("type".equals(attribute))
	        if (attributeValue == null || (!"reset".equals(attributeValue) &&
	                !"submit".equals(attributeValue) && !"button".equals(attributeValue))) {
	            attributeValue = "submit";
	            // This is needed in the decode method
	            getAttributes().put("type", attributeValue);
	        }

		return attributeValue;
	}
}
