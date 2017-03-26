package com.quakearts.webapp.facelets.tag.listener;

import java.util.Properties;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.quakearts.webapp.facelets.tag.BaseListener;

public class ListPropertiesListener extends BaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3590099442304994753L;
	private ValueExpression propertiesExpression;
	private ValueExpression varExpression;
	
	public ListPropertiesListener(ValueExpression propertiesExpression,
			ValueExpression varExpression) {
		this.propertiesExpression = propertiesExpression;
		this.varExpression = varExpression;
	}

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		Object propertiesObject = propertiesExpression.getValue(ctx.getELContext());
		if(propertiesObject==null){
			addError("Null properties", "Properties variable is null",ctx);
			return;
		}
		Properties properties = (Properties) propertiesObject;
		varExpression.setValue(ctx.getELContext(), properties.keySet());
	}

}
