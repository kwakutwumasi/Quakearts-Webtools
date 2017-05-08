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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.el.ValueExpression;
import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootFileInput extends UICommand {

    public static final String COMPONENT_FAMILY = "com.quakearts.bootstrap.fileinput";
    public static final String RENDERER_TYPE = "com.quakearts.bootstrap.fileinput.renderer";
    private static int uid = 0;
    private static final SimpleDateFormat uniqueDateFormat = new SimpleDateFormat("HHmmssS");
    private String ticket;
        
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
	
	private synchronized static int getUid() {
		if(uid==Integer.MAX_VALUE)
			uid=1;
		else
			uid++;
		return uid;
	}

	public String getTicket() {
		if(ticket==null){
			ticket = uniqueDateFormat.format(new Date())+getUid();
		}
		return ticket;
	}
	
	@Override
	public void encodeAll(FacesContext context) throws IOException {
		String accept;
		accept = ObjectExtractor.extractString(getValueExpression("accept"), context.getELContext());
		if(accept==null)
			accept = (String) getAttributes().get("accept");
		
		ValueExpression allowMultipleExpression = getValueExpression("allowMultiple");
		boolean allowMultiple = ObjectExtractor.extractBoolean(allowMultipleExpression, context.getELContext());
		if(allowMultipleExpression==null)
			allowMultiple = Boolean.parseBoolean((String) getAttributes().get("allowMultiple"));

		Map<String, Object> sessionMap = context.getExternalContext().getSessionMap();
		sessionMap.put(getTicket() + "-accept", accept);
		if(allowMultiple)
			sessionMap.put(getTicket() + "-multiple", "");
		
		super.encodeAll(context);
	}
	
	@Override
	public Object saveState(FacesContext context) {
		return new Object[]{getTicket(),super.saveState(context)};
	}
	
	@Override
	public void restoreState(FacesContext context, Object stateObject) {
		Object[] state =(Object[])stateObject;
		ticket = (String) state[0];
		super.restoreState(context, state[1]);
	}
	
	public String get(String attribute) {
		String attributeValue = ObjectExtractor.extractString(
				getValueExpression(attribute), getFacesContext().getELContext());
		if (attributeValue == null)
			attributeValue = (String) getAttributes().get(attribute);
	
		return attributeValue;
	}
}
