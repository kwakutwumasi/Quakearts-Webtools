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

import com.quakearts.webapp.facelets.tag.AbstractOrmHandler;
import com.quakearts.webapp.facelets.tag.OrmListener;
import com.quakearts.webapp.facelets.tag.listener.SaveOrUpdateObjectListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class SaveOrUpdateObjectTagHandler extends AbstractOrmHandler {

	private TagAttribute messageAttribute = getAttribute("message"),
	objectAttribute = getRequiredAttribute("object"),
	actionAttribute = getAttribute("action"),
	trimstringsAttribute = getAttribute("trimstrings");
	
	public SaveOrUpdateObjectTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected OrmListener getOrmListener(FaceletContext ctx) {
		return new SaveOrUpdateObjectListener(getValueExpression(messageAttribute, ctx, String.class),
				getValueExpression(objectAttribute, ctx, Object.class),
				getValueExpression(actionAttribute, ctx, String.class),
				getValueExpression(trimstringsAttribute, ctx, Boolean.class));
	}

}
