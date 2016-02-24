package com.quakearts.webapp.facelets.bootstrap.components;

import javax.faces.component.UIOutput;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootAjaxLoaderComponent extends UIOutput {
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.ajaxloader";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.ajaxloader.renderer";
	
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
	
	public String get(String attribute){
		String attributeValue = ObjectExtractor.extractString(
				getValueExpression(attribute), getFacesContext()
						.getELContext());
		if (attributeValue == null)
			attributeValue = (String) getAttributes().get(attribute);

		if("startTimeout".equals(attribute) || "endTimeout".equals(attribute)){
			if (attributeValue == null || !attributeValue.matches("[\\d]+"))
				attributeValue = "500";
		}
		
		return attributeValue;
	}
}
