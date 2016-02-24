package com.quakearts.webapp.facelets.tag.handler;

import javax.faces.event.ActionListener;

import org.hibernate.mapping.Map;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.ProfileListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class ProfileTagHandler extends AbstractHandler {
	
	
	private TagAttribute varAttribute = getRequiredAttribute("var");
	
	public ProfileTagHandler(TagConfig config) {
		super(config);
		
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new ProfileListener(getValueExpression(varAttribute, ctx, Map.class));
	}

}
