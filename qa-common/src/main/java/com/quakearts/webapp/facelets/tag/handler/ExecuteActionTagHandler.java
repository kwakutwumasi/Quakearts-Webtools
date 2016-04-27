package com.quakearts.webapp.facelets.tag.handler;

import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.ExecuteActionListener;

public class ExecuteActionTagHandler extends AbstractHandler {
	
	private TagAttribute listenerAttribute = getRequiredAttribute("listener");
	
	public ExecuteActionTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new ExecuteActionListener(listenerAttribute.getMethodExpression(ctx, null, new Class[]{ActionEvent.class}));
	}

}
