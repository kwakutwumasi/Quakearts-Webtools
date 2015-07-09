package com.quakearts.webapp.facelets.bootstrap.handlers;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import com.quakearts.webapp.facelets.bootstrap.components.BootDateButton;
import com.quakearts.webapp.facelets.bootstrap.components.BootDateScriptComponent;

public class BootDateButtonHandler extends ComponentHandler {

	public BootDateButtonHandler(ComponentConfig config) {
		super(config);
	}

	@Override
	public void onComponentCreated(FaceletContext ctx, UIComponent c,
			UIComponent parent) {
		
		if(!(c instanceof BootDateButton))
			throw new FaceletException("Component must be of type "+BootDateButton.class.getName());
		
		BootDateScriptComponent dateScriptComponent = new BootDateScriptComponent();
		dateScriptComponent.setDateComponent((BootDateButton)c);		
		ctx.getFacesContext().getViewRoot().addComponentResource(ctx.getFacesContext(), dateScriptComponent);		
	}
	
	

}
