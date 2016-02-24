package com.quakearts.webapp.facelets.tag.handler;

import java.io.InputStream;

import javax.faces.event.ActionListener;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.GetBytesListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class GetBytesTagHandler extends AbstractHandler {

	
	private TagAttribute streamAttribute = getRequiredAttribute("stream"),
	varAttribute = getRequiredAttribute("var"), maxSizeAttribute = getAttribute("maxSize");
	
	public GetBytesTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new GetBytesListener(getValueExpression(streamAttribute, ctx, InputStream.class),
				getValueExpression(varAttribute, ctx, byte[].class),getValueExpression(maxSizeAttribute, ctx, Object.class));
	}

}
