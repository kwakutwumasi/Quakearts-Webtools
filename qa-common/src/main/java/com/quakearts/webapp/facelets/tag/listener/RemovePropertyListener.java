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
package com.quakearts.webapp.facelets.tag.listener;

import java.util.Properties;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.quakearts.webapp.facelets.tag.BaseListener;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class RemovePropertyListener extends BaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4366062940988758937L;
	private ValueExpression nameExpression,propertiesExpression;
	
	public RemovePropertyListener(ValueExpression nameExpression,
			ValueExpression propertiesExpression) {
		this.nameExpression=nameExpression;
		this.propertiesExpression = propertiesExpression;
	}

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		String name = ObjectExtractor.extractString(nameExpression, ctx.getELContext());
		
		Object propertiesObject = propertiesExpression.getValue(ctx.getELContext());
		if(propertiesObject == null){
			addError("Null properties submitted", "Properties submitted was null",ctx);
			setOutcome("error");
			return;			
		}
		Properties properties = (Properties) propertiesObject;
		
		Object oldValue = properties.remove(name);
		addMessage("Property added", name+" has been deleted. Former value was "+oldValue, ctx);
		setOutcome("success");
	}

}
