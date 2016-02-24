package com.quakearts.webapp.facelets.tag.input;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;

public class DateTagHandler extends ComponentHandler {

	public DateTagHandler(ComponentConfig config) {
		super(config);
	}

	@Override
	public void onComponentCreated(FaceletContext ctx, UIComponent c,
			UIComponent parent) {
		
		if(!(c instanceof DateComponent))
			throw new FaceletException("Component must be of type "+DateComponent.class.getName());
		
		DateComponent dateComponent = (DateComponent) c;
		
		DateScriptComponent dateScriptComponent = new DateScriptComponent();
		dateScriptComponent.setDateComponent(dateComponent);
		
		ctx.getFacesContext().getViewRoot().addComponentResource(ctx.getFacesContext(), dateScriptComponent);		
	}
	
	

}
