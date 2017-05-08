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
package com.quakearts.webapp.facelets.validator;

import java.util.HashMap;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.validator.Validator;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public abstract class AbstractMultiComponentValidator implements Validator {
	
	protected Object extractValue(ValueExpression componentExpression, ELContext elContext) throws IllegalArgumentException{
		Object componentObject = componentExpression.getValue(elContext);
		
		return extractValue(componentObject);
	}
	
	private Object extractValue(Object componentObject) throws IllegalArgumentException{
		if(componentObject instanceof EditableValueHolder){
			Object returnObject = ((EditableValueHolder)componentObject).getValue();
			if(returnObject==null){
				returnObject = ((EditableValueHolder)componentObject).getSubmittedValue();
			}
			return returnObject;
		} else {
			throw new IllegalArgumentException("ComponentExpression must eveluate to a valid "
					+ EditableValueHolder.class.getName() + " object. It is a " + componentObject.getClass().getName());
		}
	}
	
	protected Map<String, Object> extractValues(ValueExpression componentIdsExpression, ELContext elContext, UIComponent component) 
			throws IllegalArgumentException {
		String componentIds = ObjectExtractor.extractString(componentIdsExpression, elContext);
		if(componentIds == null || componentIds.trim().isEmpty()){
			throw new IllegalArgumentException("ComponentExpression must eveluate to a non empty string of component ids");
		}
		
		String[] componentIdArray = componentIds.split("\\s");
		
		Map<String, Object> valueMap = new HashMap<>();
		
		for(String componentId:componentIdArray){
			UIComponent valueComponent = component.findComponent(componentId);
			if (valueComponent==null) {
				throw new IllegalArgumentException("Component with id "+componentId+" could not be found. Ensure that all components are within the same naming container.");
			}
			
			valueMap.put(componentId, extractValue(valueComponent));
		}
		
		return valueMap;
	}
	
	protected FacesMessage getError(String title, String summary){
		return new FacesMessage(FacesMessage.SEVERITY_ERROR, title, summary);
	}
}
