package com.quakearts.webapp.facelets.bootstrap.listeners;

import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ComponentSystemEventListener;
import com.quakearts.webapp.facelets.bootstrap.components.BootPagination;

public class BootPaginationListener implements ComponentSystemEventListener {

	@SuppressWarnings("unchecked")
	@Override
	public void processEvent(ComponentSystemEvent event) throws AbortProcessingException {
		FacesContext ctx = FacesContext.getCurrentInstance();
		List<BootPagination> paginations = (List<BootPagination>) ctx.getAttributes().get(BootPagination.PAGINATION_KEY);
		if(paginations!=null){
			for(BootPagination pagination: paginations){
				pagination.updateDataModel(ctx);
			}
		}
	}
}
