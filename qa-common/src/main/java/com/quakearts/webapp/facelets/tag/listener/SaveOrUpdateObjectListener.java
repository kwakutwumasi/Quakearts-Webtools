package com.quakearts.webapp.facelets.tag.listener;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import java.util.logging.Logger;

import com.quakearts.webapp.facelets.tag.HibernateListener;
import com.quakearts.webapp.facelets.util.ObjectExtractor;
import com.quakearts.webapp.facelets.util.UtilityMethods;

public class SaveOrUpdateObjectListener extends HibernateListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6659018184061783495L;
	private ValueExpression objectExpression;
	private ValueExpression messageExpression, actionExpression;
	private static final Logger log = Logger.getLogger(SaveOrUpdateObjectListener.class.getName());

	public SaveOrUpdateObjectListener(ValueExpression messageExpression,
			ValueExpression objectExpression, ValueExpression actionExpression) {
		this.messageExpression = messageExpression;
		this.objectExpression = objectExpression;
		this.actionExpression = actionExpression;
	}

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		Object object = objectExpression.getValue(ctx.getELContext());
		String message = ObjectExtractor.extractString(messageExpression, ctx.getELContext()),
		action = ObjectExtractor.extractString(actionExpression, ctx.getELContext());
		
		try {
			if(object!=null){
				if(action != null){
					if(action.equals("save"))
						session.save(object);						
					else if(action.equals("update"))
						session.update(object);
					else
						throw new IllegalArgumentException("Action "+action+" is not a valid parameter");
				}else{
					session.saveOrUpdate(object);
				}
				addMessage("Success",message==null?(object.getClass().getSimpleName()+" has been successfully saved"):message, ctx);
				setOutcome("success");
			}else{
				setOutcome("error");
				addError("Invalid object", "Object attribute evaluated to null", ctx);				
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
