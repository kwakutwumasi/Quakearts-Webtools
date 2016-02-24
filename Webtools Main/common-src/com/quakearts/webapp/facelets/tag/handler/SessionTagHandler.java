package com.quakearts.webapp.facelets.tag.handler;

import javax.faces.event.ActionListener;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.SessionListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class SessionTagHandler extends AbstractHandler {

	private TagAttribute varAttribute = getRequiredAttribute("var");
	private TagAttribute keyAttribute = getRequiredAttribute("key");
	private TagAttribute actionAttribute = getRequiredAttribute("action");

	public SessionTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new SessionListener(getValueExpression(varAttribute, ctx, Object.class),
				getValueExpression(keyAttribute, ctx, String.class),
				getValueExpression(actionAttribute, ctx, String.class));
	}

}
