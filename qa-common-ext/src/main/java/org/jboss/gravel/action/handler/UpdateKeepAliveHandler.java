package org.jboss.gravel.action.handler;

import java.io.IOException;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.ActionSource;
import javax.faces.component.UIComponent;
import org.jboss.gravel.action.action.UpdateKeepAliveActionListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

public class UpdateKeepAliveHandler extends TagHandler {

	
	private TagAttribute varAttribute = getRequiredAttribute("var");
	
	public UpdateKeepAliveHandler(TagConfig config) {
		super(config);
	}

	@Override
	public void apply(FaceletContext ctx, UIComponent comp)
			throws IOException, FacesException, FaceletException, ELException {
		
		if(comp != null)
			if(comp instanceof ActionSource){
				((ActionSource)comp).addActionListener(new UpdateKeepAliveActionListener(varAttribute.getValueExpression(ctx, Object.class)));
			}
	}

}
