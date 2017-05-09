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

import java.util.logging.Logger;

import com.quakearts.webapp.facelets.tag.OrmListener;
import com.quakearts.webapp.facelets.util.ObjectExtractor;
import com.quakearts.webapp.facelets.util.UtilityMethods;
import com.quakearts.webapp.orm.stringconcat.OrmStringConcatUtil;

public class SaveOrUpdateObjectListener extends OrmListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6659018184061783495L;
	private ValueExpression objectExpression;
	private ValueExpression messageExpression, actionExpression;
	private ValueExpression trimstringsExpression;
	private static final Logger log = Logger.getLogger(SaveOrUpdateObjectListener.class.getName());

	public SaveOrUpdateObjectListener(ValueExpression messageExpression,
			ValueExpression objectExpression, ValueExpression actionExpression, 
			ValueExpression trimstringsExpression) {
		this.messageExpression = messageExpression;
		this.objectExpression = objectExpression;
		this.actionExpression = actionExpression;
		this.trimstringsExpression = trimstringsExpression;
	}

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		Object object = objectExpression.getValue(ctx.getELContext());
		
		if(object == null){
			setOutcome("error");
			addError("Invalid object", "Object attribute evaluated to null", ctx);				
			return;
		}
		
		String message = ObjectExtractor.extractString(messageExpression, ctx.getELContext()),
		action = ObjectExtractor.extractString(actionExpression, ctx.getELContext());
		
		if(trimstringsExpression!=null 
				&& ObjectExtractor.extractBoolean(trimstringsExpression, ctx.getELContext())){
			OrmStringConcatUtil.trimStrings(object);
		}
		
		try {
			if(object!=null){
				if(action != null){
					if(action.equals("save"))
						dataStore.save(object);						
					else if(action.equals("update"))
						dataStore.update(object);
					else
						throw new IllegalArgumentException("Action "+action+" is not a valid parameter");
				} else {
					dataStore.saveOrUpdate(object);
				}
				addMessage("Success",message==null?(object.getClass().getSimpleName()+" has been successfully saved"):message, ctx);
				setOutcome("success");
			}
		} catch (Exception e) {
			log.severe("Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage());
			try {
				UtilityMethods.getTransaction().setRollbackOnly();
			} catch (Exception e2) {
			}
			addError("Application error", "Exception saving/updating object", ctx);
			setOutcome("error");
		}
	}

}
