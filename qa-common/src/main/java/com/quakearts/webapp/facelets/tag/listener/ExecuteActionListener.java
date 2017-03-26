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
