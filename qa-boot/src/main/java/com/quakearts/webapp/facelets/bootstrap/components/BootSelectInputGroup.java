package com.quakearts.webapp.facelets.bootstrap.components;

import javax.faces.context.FacesContext;

import com.quakearts.webapp.facelets.bootstrap.behaviour.AutoCompleteBehavior;

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
	protected void validateValue(FacesContext context, Object value) {
		if(context.getAttributes().containsKey(AutoCompleteBehavior.SUGGESTIONPRESENT)){
			return;
		}
		
		super.validateValue(context, value);
	}
	
}
