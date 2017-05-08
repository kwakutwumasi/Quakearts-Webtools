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
package com.quakearts.webapp.facelets.tag.listener;

import java.util.Properties;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.quakearts.webapp.facelets.tag.BaseListener;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class AddPropertyListener extends BaseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4147673947758291396L;
	private ValueExpression nameExpression, propertiesExpression, valueExpression;
	
	public AddPropertyListener(ValueExpression aliasExpression,
			ValueExpression propertiesExpression,ValueExpression fileExpression) {
		this.nameExpression = aliasExpression;
		this.propertiesExpression = propertiesExpression;
		this.valueExpression = fileExpression;
	}

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		String name = ObjectExtractor.extractString(nameExpression, ctx.getELContext());
		String value = ObjectExtractor.extractString(valueExpression, ctx.getELContext());
		
		Object propertiesObject = propertiesExpression.getValue(ctx.getELContext());
		if(propertiesObject == null){
			addError("Null properties submitted", "Properties submitted was null",ctx);
			setOutcome("error");
			return;			
		}
		Properties properties = (Properties) propertiesObject;
		
		properties.put(name, value);
		addMessage("Property added", name+" has been set to "+value, ctx);
		setOutcome("success");
	}

}
