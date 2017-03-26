package com.quakearts.webapp.facelets.tag.handler;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class SecureTagHandler extends TagHandler {

	private TagAttribute rolesAttribute = getRequiredAttribute("roles"), 
			accessValueAttribute = getAttribute("access-value"),
			redirectAttribute = getAttribute("redirect"), 
			messageAttribute = getAttribute("message");
	
	public SecureTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	public void apply(FaceletContext ctx, UIComponent parent) throws IOException {
		String rolesString = ObjectExtractor.extractString(rolesAttribute.getValueExpression(ctx, String.class), ctx);
		if(rolesString == null || rolesString.trim().isEmpty())
			throw new IOException("Attribute 'roles' is required.");
		
		FacesContext fctx = ctx.getFacesContext();
		ExternalContext ectx = fctx.getExternalContext();
		String[] roles = rolesString.split(",");
		boolean access = false;
		
		for(String role:roles){
			if(ectx.isUserInRole(role)){
				access = true;
				break;
			}
		}
		
		if(access){
			nextHandler.apply(ctx, parent);
		} else {
			if(redirectAttribute!=null){
				String redirect = ObjectExtractor.extractString(redirectAttribute.getValueExpression(ctx, String.class), ctx);
				if(redirect == null)
					throw new IOException("Attribute 'redirect' must not be null");
				
				ectx.redirect(redirect);
			} else if(messageAttribute!=null) {
				String message = ObjectExtractor.extractString(messageAttribute.getValueExpression(ctx, String.class), ctx);

				if(message == null)
					throw new IOException("Attribute 'message' must not be null");
				
				fctx.addMessage(parent.getClientId(), new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Security Error", message));
			}
		}
		
		if(accessValueAttribute!=null){
			accessValueAttribute.getValueExpression(ctx, Boolean.class).setValue(ctx, access);
		}
	}
}
