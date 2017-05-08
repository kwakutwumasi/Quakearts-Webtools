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
package com.quakearts.webapp.facelets.tag;

import javax.faces.event.ActionListener;

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public abstract class AbstractHibernateHandler extends AbstractHandler {

	private TagAttribute domainAttribute = getAttribute("domain");
	
	public AbstractHibernateHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		HibernateListener listener = getHibernateListener(ctx);

		if(domainAttribute != null){
			listener.setDomainExpression(getValueExpression(domainAttribute, ctx, Object.class));
		}

		return listener;
	}
	
	protected abstract HibernateListener getHibernateListener(FaceletContext ctx);

}
