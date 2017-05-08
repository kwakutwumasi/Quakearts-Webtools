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
package com.quakearts.webapp.facelets.phase;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class GenericPhaseListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7137885488865539533L;
	public static final String OUTCOME_KEY = "com.quakearts.webapp.facelets.OUTCOME", REQUESTVARS = "com.quakearts.webapp.facelets.REQUEST_VARS";
	
	@Override
	public void afterPhase(PhaseEvent event) {
		PhaseId phaseId = event.getPhaseId();
		
		if(phaseId == PhaseId.INVOKE_APPLICATION){//start a transaction
			handleNavigation();
		} 
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

	protected void handleNavigation(){
		FacesContext context = FacesContext.getCurrentInstance();
		Object obj = context.getExternalContext().getRequestMap().get(OUTCOME_KEY);
		if(obj !=null){
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			handler.handleNavigation(context, this.getClass().getSimpleName().toLowerCase(), obj.toString().toLowerCase());
		}
	}

}
