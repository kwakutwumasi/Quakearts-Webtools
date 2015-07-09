package com.quakearts.webapp.facelets.tag.input;

import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;

public class DateScriptComponent extends UIOutput {

    public static final String COMPONENT_FAMILY = "com.quakearts.facelets.input.datescript";
    public static final String COMPONENT_TYPE = "com.quakearts.facelets.input.datescript";
    private DateComponent dateComponent;

	public DateComponent getDateComponent() {
		return dateComponent;
	}

	public void setDateComponent(DateComponent dateComponent) {
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
		Object superState=super.saveState(context);
		return new Object[]{dateComponent,superState};
	}
	
	@Override
	public void restoreState(FacesContext context, Object state) {
		dateComponent = (DateComponent) ((Object[])state)[0];
		super.restoreState(context, ((Object[])state)[1]);
	}
	
	@Override
	public String getRendererType() {
		return "com.quakearts.facelets.input.datescript.renderer";
	}
}
