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
package com.quakearts.webapp.facelets.tag.listener;

import java.io.File;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import com.quakearts.webapp.facelets.tag.BaseListener;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public abstract class AbstractFileListener extends BaseListener {

	/**
	 * 
	 */
	private ValueExpression rootExpression;
	public AbstractFileListener(ValueExpression rootExpression) {
		this.rootExpression = rootExpression;
	}
	
	private static final long serialVersionUID = 6820173976429607499L;

	@Override
	protected void continueProcessing(ActionEvent event, FacesContext ctx) {
		String root;
		root = ObjectExtractor.extractString(rootExpression, ctx.getELContext());
		if(root==null||root.trim().isEmpty()){
			Object servletObject = ctx.getExternalContext().getContext();
			if(servletObject instanceof ServletContext){
				ServletContext srvctx = (ServletContext) servletObject;
				root = srvctx.getRealPath("/WEB-INF/classes");
			} else {
				addError("Context type not supported","Non "+ServletContext.class.getName()+" are not supported", ctx);
				setOutcome("error");
			}
		}
		performFileAction(event, ctx, new File(root));
	}

	abstract protected void performFileAction(ActionEvent event, FacesContext ctx, File root);
}
