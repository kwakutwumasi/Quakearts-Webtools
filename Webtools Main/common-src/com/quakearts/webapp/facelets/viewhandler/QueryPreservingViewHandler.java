package com.quakearts.webapp.facelets.viewhandler;

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class QueryPreservingViewHandler extends ViewHandlerWrapper {

    private ViewHandler wrapped;	
    
	public QueryPreservingViewHandler(ViewHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ViewHandler getWrapped() {
		return wrapped;
	}

	@Override
	public String getActionURL(FacesContext context, String viewId) {
		String actionURL = super.getActionURL(context, viewId);
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		if(request.getQueryString()!=null)
			return actionURL+"?"+request.getQueryString();
		
		return actionURL;
	}
	
}