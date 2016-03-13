package com.quakearts.webapp.facelets.bootstrap.components;

import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;

public class BootCheckbox extends HtmlSelectBooleanCheckbox {
	
	public static final String COMPONENT_FAMILY="com.quakearts.bootstrap.checkbox";
	public static final String RENDERER_TYPE="com.quakearts.bootstrap.checkbox.renderer";

	public BootCheckbox() {
		getAttributes().put("javax.faces.component.UIComponentBase.attributesThatAreSet", new ArrayList<String>());
	}
	
	/* Implemented to switch default component processing: process parent before child, in order to control 
	 * processing of the child input text component
	 * 
	 * @see javax.faces.component.UIInput#processDecodes(javax.faces.context.FacesContext)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void processDecodes(FacesContext context) {
        if (context == null) {
            throw new NullPointerException();
        }

        // Skip processing if our rendered flag is false
        if (!isRendered()) {
            return;
        }

        pushComponentToEL(context, null);

        try {
            decode(context);
            Iterator kids = getFacetsAndChildren();
            while (kids.hasNext()) {
                UIComponent kid = (UIComponent) kids.next();
                kid.processDecodes(context);
            }
        } catch (RuntimeException e) {
            context.renderResponse();
            throw e;
        } finally {
            popComponentFromEL(context);
        }
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
