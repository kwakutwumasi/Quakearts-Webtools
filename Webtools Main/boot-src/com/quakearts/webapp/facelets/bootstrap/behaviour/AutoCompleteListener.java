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
