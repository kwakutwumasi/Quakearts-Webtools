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
