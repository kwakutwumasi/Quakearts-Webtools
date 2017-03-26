package org.jboss.gravel.action.action;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ComponentSystemEventListener;
import javax.faces.event.PhaseId;

import org.jboss.gravel.action.ui.UIResponseActions;

public class ResponseActionSystemListener implements
		ComponentSystemEventListener {

	@Override
	public void processEvent(ComponentSystemEvent event)
			throws AbortProcessingException {
		List<UIResponseActions> components = (List<UIResponseActions>) FacesContext
				.getCurrentInstance().getAttributes()
				.get(UIResponseActions.RESPONSEACTION);
		
		if(components==null)
			return;
		for(UIResponseActions component:components){
	        ActionEvent actionEvent = new ActionEvent(component);
	        actionEvent.setPhaseId(PhaseId.RENDER_RESPONSE);
	        component.broadcast(actionEvent);
		}
	}

}
