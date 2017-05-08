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

import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.quakearts.webapp.facelets.tag.BaseListener;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class AddToListListener extends BaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3518573446265296736L;
	private ValueExpression targetExpression, objectExpression;
	
	public AddToListListener(ValueExpression targetExpression,
			ValueExpression objectExpression) {
		this.targetExpression = targetExpression;
		this.objectExpression = objectExpression;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		ELContext elctx = ctx.getELContext();
		List<Object> list;
		Object object;
		
		list = ObjectExtractor.extractList(targetExpression, ctx.getELContext());
		if(list == null)
			list = new ArrayList();
		
		object = ObjectExtractor.extractMap(objectExpression, elctx);
		if(object != null){
			list.add(object);
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Successfully appended variable map"));
			targetExpression.setValue(ctx.getELContext(), list);
			setOutcome("success");
		}
	}

}
