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

import java.util.List;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.quakearts.webapp.facelets.tag.BaseListener;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class RemoveFromListListener extends BaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3070221339770773915L;
	private ValueExpression listExpression, indexExpression, varExpression;
	
	public RemoveFromListListener(ValueExpression listExpression, ValueExpression indexExpression, ValueExpression varExpression) {
		this.listExpression = listExpression;
		this.indexExpression = indexExpression;
		this.varExpression = varExpression;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		ELContext elctx = ctx.getELContext();
		List list = ObjectExtractor.extractList(listExpression, elctx);
		Object obj = list.remove(ObjectExtractor.extractInteger(indexExpression, elctx));
		if(varExpression != null)
			varExpression.setValue(elctx, obj);
		
		listExpression.setValue(elctx, list);
	}

}
