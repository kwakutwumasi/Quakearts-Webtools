package com.quakearts.webapp.facelets.tag.handler;

import java.io.InputStream;
import java.util.Map;

import javax.faces.event.ActionListener;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.LoadPropertiesListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class LoadPropertiesHandler extends AbstractHandler {

	private final TagAttribute fileTagAttribute=getAttribute("file"),
			streamAttribute=getAttribute("stream"),varTagAttribute=getRequiredAttribute("var");
	
	public LoadPropertiesHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		
		return new LoadPropertiesListener(
				getValueExpression(fileTagAttribute, ctx, String.class),
				getValueExpression(varTagAttribute, ctx, Map.class),
				getValueExpression(streamAttribute, ctx, InputStream.class));
	}

}
