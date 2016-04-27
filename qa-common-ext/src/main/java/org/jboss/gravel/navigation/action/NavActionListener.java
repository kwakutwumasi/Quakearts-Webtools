package org.jboss.gravel.navigation.action;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class NavActionListener implements ActionListener, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3835134538918769465L;
	private NavigationState navigationState;
	
	public NavActionListener(NavigationState navigationState) {
		this.navigationState = navigationState;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		FacesContext context = FacesContext.getCurrentInstance();
		Map requestMap = context.getExternalContext().getRequestMap();
		if(!requestMap.containsKey(NavigationState.NAV_KEY)){
			requestMap.put(NavigationState.NAV_KEY, navigationState);
		}
	}

}
