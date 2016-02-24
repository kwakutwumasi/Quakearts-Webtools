package com.quakearts.webapp.facelets.tag.handler;

import javax.faces.event.ActionListener;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.GetOutcomeTagListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class GetOutcomeTagHandler extends AbstractHandler {
	
	
	public GetOutcomeTagHandler(TagConfig config) {
		super(config);
	}

	private TagAttribute outcomeAttribute = getRequiredAttribute("outcome");
	
	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new GetOutcomeTagListener(getValueExpression(outcomeAttribute, ctx, String.class));
	}

}
