package com.quakearts.webapp.facelets.tag.handler;

import javax.faces.event.ActionListener;

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.SetListener;

public class SetTagHandler extends AbstractHandler {

	private TagAttribute varAttribute = getRequiredAttribute("var"),
	asAttribute = getRequiredAttribute("as");
	
	public SetTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new SetListener(getValueExpression(asAttribute, ctx, Object.class),
				getValueExpression(varAttribute, ctx, Object.class));
	}

}
