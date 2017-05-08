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
package com.quakearts.webapp.facelets.tag.handler;

import java.util.Collection;
import javax.faces.event.ActionListener;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.HttpURLConnectionListener;
import com.quakearts.webapp.facelets.util.WebPageBean;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class HttpURLConnectionHandler extends AbstractHandler {

	private final TagAttribute urlAttribute, methodAttribute, targetAttribute,cookieAttribute;
	
	public HttpURLConnectionHandler(TagConfig config) {
		super(config);
		urlAttribute = getRequiredAttribute("url");
		methodAttribute = getAttribute("method");
		cookieAttribute = getAttribute("cookie");
		targetAttribute = getAttribute("cookie");
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new HttpURLConnectionListener(getValueExpression(urlAttribute,ctx,String.class),
				getValueExpression(methodAttribute,ctx,String.class),
				getValueExpression(cookieAttribute,ctx,Collection.class),
				getValueExpression(targetAttribute,ctx,WebPageBean.class));
	}

}
