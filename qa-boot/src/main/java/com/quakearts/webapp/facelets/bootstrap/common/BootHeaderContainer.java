package com.quakearts.webapp.facelets.bootstrap.common;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

public class BootHeaderContainer extends UIComponentBase {
	public static final String COMPONENT_FAMILY = "com.quakearts.bootstrap.bootheadcontainer";
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	@Override
	public boolean getRendersChildren() {
		return false;
	}
	
	@Override
	public void encodeAll(FacesContext context) throws IOException {
        for (UIComponent kid : getChildren()) {
            kid.encodeAll(context);
        }
	}
	
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
	}

	@Override
	public void encodeEnd(FacesContext context) throws IOException {
	}
}
