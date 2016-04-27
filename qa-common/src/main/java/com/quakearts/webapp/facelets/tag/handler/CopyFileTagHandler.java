package com.quakearts.webapp.facelets.tag.handler;

import javax.faces.event.ActionListener;

import com.quakearts.webapp.facelets.tag.listener.CopyFileListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class CopyFileTagHandler extends AbstractFileTagHandler {

	private TagAttribute fileTagAttribute = getRequiredAttribute("file"), newFile = getRequiredAttribute("newFile");

	public CopyFileTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new CopyFileListener(getValueExpression(fileTagAttribute, ctx, String.class),
				getValueExpression(newFile, ctx, String.class),getRoot(ctx));
	}

}
