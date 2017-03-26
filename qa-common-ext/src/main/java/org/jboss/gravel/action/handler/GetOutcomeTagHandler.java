package org.jboss.gravel.action.handler;

import java.io.IOException;

import javax.faces.component.ActionSource;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

import org.jboss.gravel.action.action.GetOutcomeTagListener;

public class GetOutcomeTagHandler extends TagHandler {
	
	
	public GetOutcomeTagHandler(TagConfig config) {
		super(config);
	}

	private TagAttribute outcomeAttribute = getRequiredAttribute("outcome");
	
	@Override
	public void apply(FaceletContext ctx, UIComponent comp) throws IOException {
		if(!(comp instanceof ActionSource))
			throw new IOException("Component not and instance of ActionSource");
		
		if(!ComponentHandler.isNew(comp))
			return;
		
		((ActionSource)comp).addActionListener(new GetOutcomeTagListener(outcomeAttribute.getValueExpression( ctx, String.class)));			
	}

}
