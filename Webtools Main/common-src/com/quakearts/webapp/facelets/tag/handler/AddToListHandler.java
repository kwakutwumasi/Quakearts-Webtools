package com.quakearts.webapp.facelets.tag.handler;

import java.util.List;
import javax.faces.event.ActionListener;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.AddToListListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class AddToListHandler extends AbstractHandler {

	private TagAttribute targetAttribute = getRequiredAttribute("target"), objectAttribute = getRequiredAttribute("object");
	
	public AddToListHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new AddToListListener(getValueExpression(targetAttribute, ctx, List.class),
				getValueExpression(objectAttribute, ctx, Object.class));
	}

}
