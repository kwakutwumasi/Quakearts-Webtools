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
package com.quakearts.webapp.facelets.tag.handler;

import java.io.IOException;
import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;

import com.quakearts.webapp.facelets.util.AutoCompleteSelectItemsBean;
import com.quakearts.webapp.facelets.util.ObjectExtractor;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

public class AutoCompleteSelectTagHandler extends TagHandler {

	private TagAttribute columnAttribute = getRequiredAttribute("column"), jndiAttribute = getRequiredAttribute("jndi"),
	labelAttribute = getRequiredAttribute("label"), tableAttribute = getRequiredAttribute("table"),
	beanAttribute = getRequiredAttribute("bean"), likeAttribute = getAttribute("like"), keyAttribute = getAttribute("key"), unlessAttribute = getAttribute("unless");
	
	public AutoCompleteSelectTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	public void apply(FaceletContext ctx, UIComponent component)
			throws IOException, FacesException, FaceletException, ELException {
		AutoCompleteSelectItemsBean bean = new AutoCompleteSelectItemsBean();
		
		if(unlessAttribute !=null){
			if(ObjectExtractor.extractBoolean(getValueExpression(unlessAttribute, ctx, Object.class), ctx)){
				return;
			}
		}
		
		bean.setColumn(ObjectExtractor.extractString(getValueExpression(columnAttribute, ctx, String.class), ctx));
		bean.setJndi(ObjectExtractor.extractString(getValueExpression(jndiAttribute, ctx, String.class), ctx));
		bean.setLabel(ObjectExtractor.extractString(getValueExpression(labelAttribute, ctx, String.class), ctx));
		bean.setTable(ObjectExtractor.extractString(getValueExpression(tableAttribute, ctx, String.class), ctx));
		
		if(keyAttribute != null)
			bean.setKey(ObjectExtractor.extractString(getValueExpression(keyAttribute, ctx, String.class), ctx));
			
		if(likeAttribute !=null)
			bean.setLike(ObjectExtractor.extractBoolean(getValueExpression(likeAttribute, ctx, Object.class), ctx));
		
		ValueExpression beanExpression = getValueExpression(beanAttribute, ctx, AutoCompleteSelectItemsBean.class);
		beanExpression.setValue(ctx, bean);		
	}

	private ValueExpression getValueExpression(TagAttribute tagAttribute,
			FaceletContext ctx, @SuppressWarnings("rawtypes") Class clazz) {
		return tagAttribute == null? null : tagAttribute.getValueExpression(ctx, clazz);
	}

}
