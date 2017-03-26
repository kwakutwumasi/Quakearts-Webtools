package com.quakearts.webapp.facelets.tag.listener;

import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;

import com.quakearts.webapp.facelets.tag.BaseListener;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class SessionListener extends BaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4147817228046629181L;
	ValueExpression varExpression, keyExpression, actionExpression;
	
	public SessionListener(ValueExpression varExpression,
			ValueExpression keyExpression, ValueExpression actionExpression) {
		this.actionExpression = actionExpression;
		this.keyExpression = keyExpression;
		this.varExpression = varExpression;
	}

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		Map<String, Object> sessionMap = ctx.getExternalContext().getSessionMap();
		String key = ObjectExtractor.extractString(keyExpression, ctx.getELContext());
		if(key == null){
			throw new AbortProcessingException("Attribute key cannot be null");
		}
		
		Object object = varExpression.getValue(ctx.getELContext());
		String action = ObjectExtractor.extractString(actionExpression,ctx.getELContext());
		if(action == null){
			throw new AbortProcessingException("Attribute action cannot be null");
		}
		
		if(action.equals("save")){
			if(object!=null){
				sessionMap.put(key, object);
			}
		} else if(action.equals("get")) {
			object = sessionMap.get(key);
			if(object!=null)
				varExpression.setValue(ctx.getELContext(), object);
		} else if(action.equals("remove")){
			sessionMap.remove(key);
		} else {
			throw new AbortProcessingException("Attribute action cannot be '"+action+"'. Must be one of 'get','save', 'remove'");
		}
	}

}
