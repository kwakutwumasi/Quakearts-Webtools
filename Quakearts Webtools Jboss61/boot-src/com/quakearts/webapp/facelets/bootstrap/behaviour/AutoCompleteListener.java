package com.quakearts.webapp.facelets.bootstrap.behaviour;

import javax.el.MethodExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;

public class AutoCompleteListener implements AjaxBehaviorListener {

	private MethodExpression autocompleteListenerExpression;
	
	public AutoCompleteListener(MethodExpression autocompleteListenerExpression) {
		this.autocompleteListenerExpression = autocompleteListenerExpression;
	}
	
	@Override
	public void processAjaxBehavior(AjaxBehaviorEvent event) throws AbortProcessingException {
		autocompleteListenerExpression.invoke(FacesContext.getCurrentInstance().getELContext(), new Object[]{event});
	}

}
