package com.quakearts.webapp.facelets.tag.handler;

import java.util.List;

import javax.faces.event.ActionListener;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.RemoveFromListListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class RemoveFromListHandler extends AbstractHandler {

	private TagAttribute listAttribute=getRequiredAttribute("list"), indexAttribute=getRequiredAttribute("index"),
	varAttribute = getAttribute("var");
	
	public RemoveFromListHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new RemoveFromListListener(getValueExpression(listAttribute, ctx, List.class),
				getValueExpression(indexAttribute, ctx, Long.class),
				getValueExpression(varAttribute, ctx, Object.class));
	}

}
