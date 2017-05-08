/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.component.EditableValueHolder;
import javax.faces.validator.Validator;

public abstract class MultiComponentValidator implements Validator {
	
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
}
