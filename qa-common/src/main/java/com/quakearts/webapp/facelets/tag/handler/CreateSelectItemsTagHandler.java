package com.quakearts.webapp.facelets.tag.handler;

import java.util.List;
import javax.faces.event.ActionListener;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.CreateSelectItemsListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class CreateSelectItemsTagHandler extends AbstractHandler {

	private TagAttribute listTagAttribute = getRequiredAttribute("list"), 
	varTagAttribute = getRequiredAttribute("var"); 
	
	public CreateSelectItemsTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new CreateSelectItemsListener(getValueExpression(listTagAttribute, ctx, String.class),
				getValueExpression(varTagAttribute, ctx, List.class));
	}

}
