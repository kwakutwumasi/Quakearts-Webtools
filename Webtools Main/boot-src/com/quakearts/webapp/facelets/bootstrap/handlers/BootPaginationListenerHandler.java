package com.quakearts.webapp.facelets.bootstrap.handlers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

import com.quakearts.webapp.facelets.bootstrap.components.BootPagination;
import com.quakearts.webapp.facelets.bootstrap.listeners.PaginationActionListener;

public class BootPaginationListenerHandler extends TagHandler {
	
	public BootPaginationListenerHandler(TagConfig config) {
		super(config);
	}

	@Override
	public void apply(FaceletContext ctx, UIComponent parent)
			throws IOException {
		
		if(!(parent instanceof BootPagination))
			throw new IOException("Parent must be of type "+BootPagination.class.getName());
		
		if(ComponentHandler.isNew(parent))
			addListener(ctx, ((BootPagination)parent));
	}

	protected void addListener(FaceletContext ctx, BootPagination parent) throws IOException{
		TagAttribute valueAttribute = getRequiredAttribute("value");
		Object object = valueAttribute.getObject(ctx, PaginationActionListener.class);
		if(object instanceof PaginationActionListener)
			parent.addPaginationListener(((PaginationActionListener)object));
		else
			throw new IOException("listener must be of type "+PaginationActionListener.class.getName());
	}
	
}
