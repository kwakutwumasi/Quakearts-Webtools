package com.quakearts.webapp.facelets.bootstrap.common;

import static com.quakearts.webapp.facelets.bootstrap.handlers.BootBaseHandler.POSITION_BOTTOM;
import static com.quakearts.webapp.facelets.bootstrap.handlers.BootBaseHandler.POSITION_TOP;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;

public abstract class BootResourceBase extends UIComponentBase {

	public void addToTop(UIComponent resource) {
		UIComponent component = getFacet(POSITION_TOP);
		if(component==null){
			getFacets().put(POSITION_TOP, resource);
		} else if(component instanceof BootHeaderContainer){
			((BootHeaderContainer)component).getChildren().add(resource);
		} else {
			BootHeaderContainer container = new BootHeaderContainer();
			container.getChildren().add(component);
			container.getChildren().add(resource);
			getFacets().put(POSITION_TOP, container);
		}
	}

	public void addToBottom(UIComponent resource) {
		UIComponent component = getFacet(POSITION_BOTTOM);
		if(component==null){
			getFacets().put(POSITION_BOTTOM, resource);
		} else if(component instanceof BootHeaderContainer){
			((BootHeaderContainer)component).getChildren().add(resource);
		} else {
			BootHeaderContainer container = new BootHeaderContainer();
			container.getChildren().add(component);
			container.getChildren().add(resource);
			getFacets().put(POSITION_BOTTOM, container);
		}
	}

}