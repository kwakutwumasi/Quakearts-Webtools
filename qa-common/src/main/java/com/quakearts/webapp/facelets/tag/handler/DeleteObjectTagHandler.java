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

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

import com.quakearts.webapp.facelets.tag.AbstractOrmHandler;
import com.quakearts.webapp.facelets.tag.OrmListener;
import com.quakearts.webapp.facelets.tag.listener.DeleteObjectListener;

public class DeleteObjectTagHandler extends AbstractOrmHandler {

	private TagAttribute messageAttribute = getAttribute("message"),
	objectAttribute = getRequiredAttribute("object");
	
	public DeleteObjectTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected OrmListener getOrmListener(FaceletContext ctx) {
		return new DeleteObjectListener(getValueExpression(messageAttribute, ctx, String.class),
				getValueExpression(objectAttribute, ctx, Object.class));
	}

}
