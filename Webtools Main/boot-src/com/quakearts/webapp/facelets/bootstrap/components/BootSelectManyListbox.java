package com.quakearts.webapp.facelets.bootstrap.components;

import java.util.ArrayList;

import javax.faces.component.html.HtmlSelectManyListbox;

public class BootSelectManyListbox extends HtmlSelectManyListbox {
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.selectManyListBox";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.select.renderer";

	public BootSelectManyListbox() {
		getAttributes().put("javax.faces.component.UIComponentBase.attributesThatAreSet", new ArrayList<String>());
	}
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public String getRendererType() {
		return RENDERER_TYPE;
	}
	
	@Override
	public void setRendererType(String rendererType) {
	}	
}
