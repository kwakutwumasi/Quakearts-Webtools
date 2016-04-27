package com.quakearts.webapp.facelets.tag.handler;

import javax.faces.event.ActionListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.LogoutActionListener;

public class LogoutTagHandler extends AbstractHandler {
	
	private TagAttribute redirectAttribute = getAttribute("redirect");
	
	public LogoutTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new LogoutActionListener(getValueExpression(redirectAttribute, ctx, String.class));
	}

}
