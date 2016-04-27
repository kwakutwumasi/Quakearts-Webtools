package com.quakearts.webapp.facelets.tag.handler;

import javax.faces.event.ActionListener;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.CopyListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class CopyTagHandler extends AbstractHandler {

	private TagAttribute copyAttribute = getRequiredAttribute("copy"),
	toAttribute = getRequiredAttribute("to");
	
	public CopyTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new CopyListener(getValueExpression(copyAttribute, ctx, Object.class),
				getValueExpression(toAttribute, ctx, Object.class));
	}

}
