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
package com.quakearts.webapp.facelets.tag;

import java.io.Serializable;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import com.quakearts.webapp.facelets.base.BaseBean;

public abstract class BaseListener extends BaseBean implements ActionListener,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8614908016864040646L;
	private ValueExpression unlessExpression;
	
	protected abstract void continueProcessing(ActionEvent event, FacesContext ctx);

	@Override
	public void processAction(ActionEvent event) throws AbortProcessingException {
		if(evaluateUnless())
			continueProcessing(event, FacesContext.getCurrentInstance());
	}
	
	public void setUnlessExpression(ValueExpression unlessExpression) {
		this.unlessExpression = unlessExpression;
	}

	public ValueExpression getUnlessExpression() {
		return unlessExpression;
	}
	
	public boolean evaluateUnless(){
		FacesContext ctx = FacesContext.getCurrentInstance();
		boolean execute=true;
		if(unlessExpression !=null){
			Object unlessValue = unlessExpression.getValue(ctx.getELContext());
			if(unlessValue instanceof Boolean){
				execute = !(Boolean)unlessValue;
			} else if(unlessValue!=null){
				execute = !Boolean.parseBoolean(unlessValue.toString());
			}
		}
		return execute;
	}
}
