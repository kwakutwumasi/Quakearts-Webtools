package com.quakearts.webapp.facelets.bootstrap.components;

import javax.el.ValueExpression;
import javax.faces.component.UIOutput;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.FacesContext;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootBreadCrumbItem extends UIOutput implements ClientBehaviorHolder {
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.breadcrumbitem";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.breadcrumbitem.renderer";
	private boolean active;
	private HtmlCommandLink commandLink;
	
	public void setCommandLink(HtmlCommandLink commandLink) {
		this.commandLink = commandLink;
	}
	
	public HtmlCommandLink getCommandLink() {
		return commandLink;
	}
	
	public boolean isActive() {
		return active;
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
	
	public void checkActive(FacesContext context){
		Object valueObject = getValue();
		String viewId = context.getViewRoot().getViewId();
    	if(valueObject!=null && viewId.contains(valueObject.toString())){
    		active = true;
    	} else {
    		ValueExpression activateExpression;
    		if((activateExpression = getValueExpression("activate"))!=null)
    			active = ObjectExtractor.extractBoolean(activateExpression, context.getELContext());
    		else
    			active = Boolean.parseBoolean((String) getAttributes().get("activate"));
    	}
	}
	
	public String get(String attribute) {
		String attributeValue = ObjectExtractor
				.extractString(getValueExpression(attribute), getFacesContext()
						.getELContext());
		if (attributeValue == null)
			attributeValue = (String) getAttributes().get(attribute);

		return attributeValue;
	}
}
