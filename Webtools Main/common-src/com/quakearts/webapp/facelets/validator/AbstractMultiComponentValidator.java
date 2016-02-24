package com.quakearts.webapp.facelets.validator;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.validator.Validator;

public abstract class AbstractMultiComponentValidator implements Validator {
	
	protected Object extractValue(ValueExpression componentExpression, ELContext elContext) throws IllegalArgumentException{
		Object componentObject = componentExpression.getValue(elContext);
		if(componentObject instanceof EditableValueHolder){
			Object returnObject = ((EditableValueHolder)componentObject).getValue();
			if(returnObject==null){
				returnObject = ((EditableValueHolder)componentObject).getSubmittedValue();
			}
			return returnObject;
		} else {
			throw new IllegalArgumentException("ComponentExpression must eveluate to a valid "+EditableValueHolder.class.getName()+" object");
		}
	}
	
	protected FacesMessage getError(String title, String summary){
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, title, summary);
	}
}
