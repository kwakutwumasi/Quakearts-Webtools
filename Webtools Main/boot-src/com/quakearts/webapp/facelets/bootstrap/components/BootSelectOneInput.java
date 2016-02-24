package com.quakearts.webapp.facelets.bootstrap.components;

public class BootSelectOneInput extends BootSelectOneMenu {
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.selectoneinput";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.selectoneinput.renderer";

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	@Override
	public String getRendererType() {
		return RENDERER_TYPE;
	}

}
