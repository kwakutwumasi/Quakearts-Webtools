package com.quakearts.webapp.facelets.bootstrap.handlers;

import javax.faces.component.UIComponent;
import javax.faces.event.AbortProcessingException;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.FaceletContext;
import com.quakearts.webapp.facelets.bootstrap.common.BootHeaderComponent;
import com.quakearts.webapp.facelets.bootstrap.components.BootAjaxLoaderComponent;
import com.quakearts.webapp.facelets.bootstrap.components.BootAjaxLoaderScriptComponent;
import static com.quakearts.webapp.facelets.bootstrap.common.BootHeaderComponent.*;

public class BootAjaxLoaderHandler extends BootBaseHandler {
	
	public BootAjaxLoaderHandler(ComponentConfig config) {
		super(config);
	}
		
	@Override
	public void onComponentCreated(FaceletContext ctx, UIComponent component,
			UIComponent parent) {
		
		if(!(component instanceof BootAjaxLoaderComponent))
			throw new AbortProcessingException("Component must be of type "
					+ BootAjaxLoaderComponent.class.getName());
		
		if(component.getValueExpression("mainloaderimage")==null && component.getAttributes().get("mainloaderimage")==null)
			throw new AbortProcessingException("Attribute mainloaderimage is required");

		if(component.getValueExpression("miniloaderimage")==null && component.getAttributes().get("miniloaderimage")==null)
			throw new AbortProcessingException("Attribute miniloaderimage is required");
		
		BootHeaderComponent headerComponent = (BootHeaderComponent) ctx
				.getFacesContext().getAttributes()
				.get(BOOT_LIBRARY_LOADED);
		
		BootAjaxLoaderScriptComponent scriptComponent = new BootAjaxLoaderScriptComponent();
		
		scriptComponent.setLoaderComponent((BootAjaxLoaderComponent)component);
		headerComponent.addToBottom(scriptComponent);
	}
	

}
