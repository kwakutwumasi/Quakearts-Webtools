package com.quakearts.webapp.facelets.tag.handler;

import java.util.Map;

import javax.faces.event.ActionListener;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.RemoveFromMapListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class RemoveFromMapHandler extends AbstractHandler {

	
	private TagAttribute mapAttribute = getRequiredAttribute("map"), keysAttribute = getRequiredAttribute("keys"),
	varAttribute = getAttribute("var");
	
	public RemoveFromMapHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new RemoveFromMapListener(getValueExpression(keysAttribute, ctx, Object.class),
				getValueExpression(mapAttribute, ctx, Map.class),
				getValueExpression(varAttribute, ctx, Map.class));
	}

}
