package com.quakearts.webapp.facelets.tag.handler;

import java.util.Properties;

import javax.faces.event.ActionListener;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.RemovePropertyListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class RemovePropertyTagHandler extends AbstractHandler {

	private TagAttribute nameAttribute = getRequiredAttribute("name"),
						 propertiesAttribute = getAttribute("properties");
	
	public RemovePropertyTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new RemovePropertyListener(getValueExpression(nameAttribute, ctx, String.class),
				getValueExpression(propertiesAttribute, ctx, Properties.class));
	}

}
