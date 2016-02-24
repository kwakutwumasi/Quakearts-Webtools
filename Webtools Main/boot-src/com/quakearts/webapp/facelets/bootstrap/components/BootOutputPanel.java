package com.quakearts.webapp.facelets.bootstrap.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.component.UIOutput;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootOutputPanel extends UIOutput {
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.outputpanel";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.outputpanel.renderer";

	protected enum PropertyKeys {
        onclick,
        ondblclick,
        onmousedown,
        onmousemove,
        onmouseout,
        onmouseover,
        onmouseup,
        style,
        title,;
        
        String toString;
        PropertyKeys(String toString) { this.toString = toString; }
        PropertyKeys() { }
        public String toString() {
            return ((toString != null) ? toString : super.toString());
        }
	}
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	public String getOnclick() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.onclick);
	}

	public void setOnclick(String onclick) {
		getStateHelper().put(PropertyKeys.onclick,onclick);
		handleAttribute("onclick",onclick);
	}

	public String getOndblclick() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.ondblclick);
	}

	public void setOndblclick(String ondblclick) {
		getStateHelper().put(PropertyKeys.ondblclick, ondblclick);
		handleAttribute("ondblclick",ondblclick);
	}

	public String getOnmousedown() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.onmousedown);
	}

	public void setOnmousedown(String onmousedown) {
		getStateHelper().put(PropertyKeys.onmousedown,onmousedown );
		handleAttribute("onmousedown",onmousedown);
	}

	public String getOnmousemove() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.onmousemove);
	}

	public void setOnmousemove(String onmousemove) {
		getStateHelper().put(PropertyKeys.onmousemove, onmousemove);
		handleAttribute("onmousemove",onmousemove);
	}

	public String getOnmouseout() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.onmouseout);
	}

	public void setOnmouseout(String onmouseout) {
		getStateHelper().put(PropertyKeys.onmouseout,onmouseout );
		handleAttribute("onmouseout",onmouseout);
	}

	public String getOnmouseover() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.onmouseover);
	}

	public void setOnmouseover(String onmouseover) {
		getStateHelper().put(PropertyKeys.onmouseover,onmouseover );
		handleAttribute("onmouseover",onmouseover);
	}

	public String getOnmouseup() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.onmouseup);
	}

	public void setOnmouseup(String onmouseup) {
		getStateHelper().put(PropertyKeys.onmouseup,onmouseup );
		handleAttribute("onmouseup",onmouseup);
	}

	public String getStyle() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.style);
	}

	public void setStyle(String style) {
		getStateHelper().put(PropertyKeys.style,style );
		handleAttribute("style",style);
	}

	public String getTitle() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.title);
	}

	public void setTitle(String title) {
		getStateHelper().put(PropertyKeys.title, title);
		handleAttribute("title",title);
	}
	
	private static final HashMap<String, String> VALID_TYPES = new HashMap<String, String>();
	static{
		VALID_TYPES.put("default", "");
		VALID_TYPES.put("primary", "");
		VALID_TYPES.put("success", "");
		VALID_TYPES.put("info", "");
		VALID_TYPES.put("warning", "");
		VALID_TYPES.put("danger", "");
	}
	
	public boolean isValid(String type){
		return VALID_TYPES.get(type)!=null;
	}

	@Override
	public String getRendererType() {
		return RENDERER_TYPE;
	}

	@Override
	public void setRendererType(String rendererType) {
	}
	
	@SuppressWarnings("unchecked")
	private void handleAttribute(String name, String value) {
		List<String> setAttributes = (List<String>) this.getAttributes().get("javax.faces.component.UIComponentBase.attributesThatAreSet");
        if (setAttributes == null) {
            setAttributes = new ArrayList<String>(6);
            this.getAttributes().put("javax.faces.component.UIComponentBase.attributesThatAreSet", setAttributes);
        }
        if (setAttributes != null) {
            if (value == null) {
                ValueExpression ve = getValueExpression(name);
                if (ve == null) {
                    setAttributes.remove(name);
                }
            } else if (!setAttributes.contains(name)) {
                setAttributes.add(name);
            }
        }		
	}
	
	public String get(String attribute) {
		String attributeValue = ObjectExtractor
				.extractString(getValueExpression(attribute), getFacesContext()
						.getELContext());
		if (attributeValue == null)
			attributeValue = (String) getAttributes().get(attribute);

		if("type".equals(attribute))
			if(attributeValue==null) {
	        	attributeValue="default";
	        } else {
		        if(isValid(attributeValue))
		        	attributeValue="default";
	        }
	                

		
		return attributeValue;
	}
}
