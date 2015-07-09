package com.quakearts.webapp.facelets.tag.handler;

import javax.faces.event.ActionListener;
import com.quakearts.webapp.facelets.tag.listener.CreateFileListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class CreateFileTagHandler extends AbstractFileTagHandler {

	private TagAttribute fileTagAttribute = getRequiredAttribute("file");

	public CreateFileTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new CreateFileListener(getValueExpression(fileTagAttribute, ctx, String.class), getRoot(ctx));
	}

}
