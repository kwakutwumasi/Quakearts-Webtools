package com.quakearts.webapp.facelets.bootstrap.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PreRenderViewEvent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.FaceletContext;

import com.quakearts.webapp.facelets.bootstrap.components.BootPagination;
import com.quakearts.webapp.facelets.bootstrap.listeners.BootPaginationListener;

public class BootPaginationHandler extends BootBaseHandler {

	public BootPaginationHandler(ComponentConfig config) {
		super(config);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void onComponentCreated(FaceletContext ctx, UIComponent c,
			UIComponent parent) {
		if(!(c instanceof BootPagination))
			throw new AbortProcessingException("Component must be of type "+BootPagination.class.getName());
				
		List<BootPagination> paginations = (List<BootPagination>) ctx.getFacesContext().getAttributes().get(BootPagination.PAGINATION_KEY);
		if(paginations==null){ //Do one time actions. Create pagination array, subscribe to event
			paginations = new ArrayList<BootPagination>();
			ctx.getFacesContext().getAttributes().put(BootPagination.PAGINATION_KEY,paginations);
			if(!ctx.getFacesContext().isPostback())
				ctx.getFacesContext().getViewRoot().subscribeToEvent(PreRenderViewEvent.class, new BootPaginationListener());
		}
		paginations.add((BootPagination)c);
	}
}
