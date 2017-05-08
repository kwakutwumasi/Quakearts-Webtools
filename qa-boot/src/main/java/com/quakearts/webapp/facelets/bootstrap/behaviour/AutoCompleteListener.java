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
package com.quakearts.webapp.facelets.bootstrap.behaviour;

import java.io.Serializable;

import javax.el.MethodExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;

public class AutoCompleteListener implements AjaxBehaviorListener, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1532408122103215298L;
	private MethodExpression autocompleteListenerExpression;
	
	public AutoCompleteListener() {
	}

	public MethodExpression getAutocompleteListenerExpression() {
		return autocompleteListenerExpression;
	}
	
	public void setAutocompleteListenerExpression(MethodExpression autocompleteListenerExpression) {
		this.autocompleteListenerExpression = autocompleteListenerExpression;
	}
	
	public AutoCompleteListener(MethodExpression autocompleteListenerExpression) {
		this.autocompleteListenerExpression = autocompleteListenerExpression;
	}
	
	@Override
	public void processAjaxBehavior(AjaxBehaviorEvent event) throws AbortProcessingException {
		autocompleteListenerExpression.invoke(FacesContext.getCurrentInstance().getELContext(), new Object[]{event});
	}

}
