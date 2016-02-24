package com.quakearts.webapp.facelets.bootstrap.components;

import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;

public class BootDateScriptComponent extends UIOutput {

    public static final String COMPONENT_FAMILY = "com.quakearts.bootstrap.datescript";
    public static final String RENDERER_TYPE = "com.quakearts.bootstrap.datescript.renderer";
    private BootDateButton dateComponent;

	public BootDateButton getDateComponent() {
		return dateComponent;
	}

	public void setDateComponent(BootDateButton dateComponent) {
		this.dateComponent = dateComponent;
	}

	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	@Override
	public boolean getRendersChildren() {
		return false;
	}
	
	@Override
	public Object saveState(FacesContext context) {
		return new Object[]{dateComponent,super.saveState(context)};
	}
	
	@Override
	public void restoreState(FacesContext context, Object state) {
		dateComponent = (BootDateButton) ((Object[])state)[0];
		super.restoreState(context, ((Object[])state)[1]);
	}
	
	@Override
	public String getRendererType() {
		return RENDERER_TYPE;
	}
	
	@Override
	public void setRendererType(String rendererType) {
	}
}
