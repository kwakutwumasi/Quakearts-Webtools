package com.quakearts.webapp.facelets;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentHandler;

public class ComponentSupport {

	public static boolean isNew(UIComponent parent) {
		return ComponentHandler.isNew(parent);
	}

}
