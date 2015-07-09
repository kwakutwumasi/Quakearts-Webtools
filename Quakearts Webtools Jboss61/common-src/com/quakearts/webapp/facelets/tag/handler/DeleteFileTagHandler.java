package com.quakearts.webapp.facelets.tag.handler;

import javax.faces.event.ActionListener;
import com.quakearts.webapp.facelets.tag.listener.DeleteFileListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class DeleteFileTagHandler extends AbstractFileTagHandler {

	private TagAttribute fileTagAttribute = getRequiredAttribute("file");

	public DeleteFileTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new DeleteFileListener(getValueExpression(fileTagAttribute, ctx, String.class),
				getRoot(ctx));
		}

}
