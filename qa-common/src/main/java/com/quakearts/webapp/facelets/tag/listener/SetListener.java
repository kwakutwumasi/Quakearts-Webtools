package com.quakearts.webapp.facelets.tag.listener;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.quakearts.webapp.facelets.tag.BaseListener;

public class SetListener extends BaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1955651570473151150L;
	private ValueExpression copyExpression, toExpression;
	
	public SetListener(ValueExpression copyExpression,
			ValueExpression toExpression) {
		this.copyExpression = copyExpression;
		this.toExpression = toExpression;
	}

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		ELContext el = ctx.getELContext();
		toExpression.setValue(el, copyExpression.getValue(el));
	}

}
