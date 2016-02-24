package com.quakearts.webapp.facelets.tag.handler;

import javax.faces.event.ActionListener;

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.AddMessageListener;

public class AddMessageTagHandler extends AbstractHandler {
	
	
	public AddMessageTagHandler(TagConfig config) {
		super(config);
	}

	private TagAttribute messageAttribute = getRequiredAttribute("message"), typeAttribute = getAttribute("type"),
			titleAttribute = getAttribute("title");
	
	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new AddMessageListener(getValueExpression(messageAttribute, ctx, String.class),
				getValueExpression(typeAttribute, ctx, String.class),
				getValueExpression(titleAttribute, ctx, String.class));
	}

}
