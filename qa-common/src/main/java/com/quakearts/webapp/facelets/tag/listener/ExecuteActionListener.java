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

import javax.el.MethodExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.quakearts.webapp.facelets.tag.BaseListener;

public class ExecuteActionListener extends BaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5028045107476841411L;
	private MethodExpression actionListenerExpression;

	public ExecuteActionListener(MethodExpression actionListenerExpression) {
		this.actionListenerExpression = actionListenerExpression;
	}

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		actionListenerExpression.invoke(ctx.getELContext(), new Object[]{event});
	}
}
