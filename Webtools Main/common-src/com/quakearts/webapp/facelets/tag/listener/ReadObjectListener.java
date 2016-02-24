package com.quakearts.webapp.facelets.tag.listener;

import java.io.Serializable;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import java.util.logging.Logger;

import com.quakearts.webapp.facelets.tag.HibernateListener;
import com.quakearts.webapp.facelets.util.ObjectExtractor;
import com.quakearts.webapp.facelets.util.UtilityMethods;

public class ReadObjectListener extends HibernateListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6659018184061783495L;
	private ValueExpression objectExpression;
	private ValueExpression messageExpression, idExpression, typeExpression;
	private static final Logger log = Logger.getLogger(ReadObjectListener.class.getName());

	public ReadObjectListener(ValueExpression messageExpression,
			ValueExpression objectExpression, ValueExpression idExpression, ValueExpression typeExpression) {
		this.messageExpression = messageExpression;
		this.objectExpression = objectExpression;
		this.idExpression = idExpression;
		this.typeExpression = typeExpression;
	}

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		Object object;
		Serializable id = (Serializable) idExpression.getValue(ctx.getELContext());
		String message = ObjectExtractor.extractString(messageExpression, ctx.getELContext());
		Class<?> type = (Class<?>) typeExpression.getValue(ctx.getELContext());
		
		if(type==null){
			addError("Invalid class type", "Attribute type evaluated to null", ctx);							
		}
		
		try {
			if(id!=null && type!=null){
				object = session.get(type, id);
				if(object!=null){
					addMessage("Loaded", message==null?(type.getSimpleName()+" has been successfully loaded"):message, ctx);
					setOutcome("success");
					objectExpression.setValue(ctx.getELContext(), object);
				} else {
					addError("Error", "Object with id "+id+" could not be found in the database", ctx);				
				}
			} else {
				setOutcome("error");
				addError("Invalid parameters", "Attribute "+(id==null?"id":"type")+" evaluated to null", ctx);				
			}
		} catch (Exception e) {
			log.severe("Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage());
			try {
				UtilityMethods.getTransaction().setRollbackOnly();
			} catch (Exception e2) {
			}
			addError("Application error", "Exception reading object", ctx);
			setOutcome("error");
		}
	}

}
