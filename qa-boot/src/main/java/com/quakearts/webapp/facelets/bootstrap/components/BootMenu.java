package com.quakearts.webapp.facelets.bootstrap.components;

import java.util.HashMap;

import javax.faces.component.UIOutput;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootMenu extends UIOutput {
    public static final String COMPONENT_FAMILY = "com.quakearts.bootstrap.menu";
    public static final String RENDERER_TYPE = "com.quakearts.bootstrap.menu.renderer";
    
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

	private static final HashMap<String, String> VALID_TYPES = new HashMap<String, String>();
	static{
		VALID_TYPES.put("default", "");
		VALID_TYPES.put("navbar", "");
	}
	
	public boolean isValid(String type){
		return VALID_TYPES.get(type)!=null;
	}
	
	public String get(String attribute) {
		String attributeValue = ObjectExtractor
				.extractString(getValueExpression(attribute), getFacesContext()
						.getELContext());
		if (attributeValue == null)
			attributeValue = (String) getAttributes().get(attribute);

		if("displayType".equals(attribute)){
	    	if(attributeValue==null || (!attributeValue.equals("default"))
	        		&& (!attributeValue.equals("primary")) && (!attributeValue.equals("success"))
	        		&& (!attributeValue.equals("info")) && (!attributeValue.equals("warning"))
	        		&& (!attributeValue.equals("danger")))
	        		attributeValue = "default";
		}
		
		return attributeValue;
	}
}
