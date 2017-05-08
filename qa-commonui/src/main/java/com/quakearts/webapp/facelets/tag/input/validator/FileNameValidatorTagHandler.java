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
package com.quakearts.webapp.facelets.tag.input.validator;

import java.io.IOException;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

public class FileNameValidatorTagHandler extends TagHandler {

	private TagAttribute restrictedTagAttribute = getAttribute("restricted"),
			transientTagAttribute = getAttribute("transient"),patternAttribute = getAttribute("pattern");
	
	public FileNameValidatorTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	public void apply(FaceletContext ctx, UIComponent parent)
			throws IOException, FacesException, FaceletException, ELException {
		if(! (parent instanceof EditableValueHolder))
			throw new FacesException("Parent must be of type "+EditableValueHolder.class.getName());
		
		FileNameValidator validator = new FileNameValidator();
		if(restrictedTagAttribute != null){
			ValueExpression restrictedExpression = restrictedTagAttribute.getValueExpression(ctx, String.class);
			Object restrictedObject = restrictedExpression.getValue(ctx);
			if(restrictedObject !=null){
				String[] restricted = restrictedObject.toString().split(",");
				validator.setRestrictions(restricted);
			}
		}
		if(transientTagAttribute != null){
			ValueExpression transientExpression = transientTagAttribute.getValueExpression(ctx, Object.class);
			Object transientObject = transientExpression.getValue(ctx);
			if(transientObject != null)
				validator.setTransient(Boolean.parseBoolean(transientObject.toString()));
		}
		if(patternAttribute!=null){
			ValueExpression patternExpression = patternAttribute.getValueExpression(ctx, String.class);
			Object patternObject = patternExpression.getValue(ctx);
			if(patternObject !=null)
				validator.setPattern(patternObject.toString());
		}
		
		((EditableValueHolder)parent).addValidator(validator);
	}
}
