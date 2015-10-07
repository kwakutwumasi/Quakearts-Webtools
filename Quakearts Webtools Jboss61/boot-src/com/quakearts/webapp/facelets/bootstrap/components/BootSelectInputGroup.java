package com.quakearts.webapp.facelets.bootstrap.components;

public class BootSelectInputGroup extends BootSelectOneMenu {
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.selectinputgroup";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.selectinputgroup.renderer";

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
