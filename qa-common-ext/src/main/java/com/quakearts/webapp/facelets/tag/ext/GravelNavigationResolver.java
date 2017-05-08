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
package com.quakearts.webapp.facelets.tag.ext;

import javax.faces.context.FacesContext;

import org.jboss.gravel.navigation.GravelNavigationHandler;

import com.quakearts.webapp.facelets.tag.NavigationResolver;

public class GravelNavigationResolver implements NavigationResolver {

	@Override
	public void setOutcome(String outcome) {
		FacesContext context = FacesContext.getCurrentInstance();

		String oldOutcome = (String) context.getExternalContext().getRequestMap().get(GravelNavigationHandler.NAV_OUTCOME_KEY);
		if(oldOutcome!=null && oldOutcome.equalsIgnoreCase("error"))//don't overwrite an error state
			return;

		context.getExternalContext().getRequestMap().put(GravelNavigationHandler.NAV_OUTCOME_KEY,outcome);
	}

}
