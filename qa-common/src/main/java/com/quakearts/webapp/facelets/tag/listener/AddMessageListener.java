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

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.quakearts.webapp.facelets.tag.BaseListener;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class AddMessageListener extends BaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -722626912038757739L;
	ValueExpression messageExpression, typeExpression,titleExpression;
	
	public AddMessageListener(ValueExpression messageExpression, ValueExpression typeExpression, ValueExpression titleExpression) {
		this.messageExpression = messageExpression;
		this.typeExpression = typeExpression;
		this.titleExpression = titleExpression;
	}

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		String message = ObjectExtractor.extractString(messageExpression, ctx.getELContext()),
				type = ObjectExtractor.extractString(typeExpression, ctx.getELContext()),
				title = ObjectExtractor.extractString(titleExpression, ctx.getELContext());

		if(type!=null && type.equals("error"))
			addWarning(title==null?"":title, message==null?"":message,ctx);
		else if(type!=null && type.equals("warn"))
			addError(title==null?"":title, message==null?"":message,ctx);
		else
			addMessage(title==null?"":title, message==null?"":message,ctx);
	}

}
