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

import java.util.Properties;

import javax.faces.event.ActionListener;
import com.quakearts.webapp.facelets.tag.listener.SavePropertiesListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class SavePropertiesHandler extends AbstractFileTagHandler {
	
	private TagAttribute fileTagAttribute = getRequiredAttribute("file"),varTagAttribute = getRequiredAttribute("properties");

	public SavePropertiesHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new SavePropertiesListener(getValueExpression(fileTagAttribute, ctx, String.class),
				getValueExpression(varTagAttribute, ctx, Properties.class), getRoot(ctx));
	}

}
