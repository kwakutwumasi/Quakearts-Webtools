package com.quakearts.webapp.facelets.tag.handler;

import java.util.Properties;

import javax.faces.event.ActionListener;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.AddPropertyListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class AddPropertyTagHandler extends AbstractHandler {

	private TagAttribute nameTagAttribute = getRequiredAttribute("name"),
	propertiesTagAttribute = getRequiredAttribute("properties"),
	valueTagAttribute = getRequiredAttribute("value");

	public AddPropertyTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new AddPropertyListener(getValueExpression(nameTagAttribute, ctx, String.class),
				getValueExpression(propertiesTagAttribute, ctx, Properties.class),
				getValueExpression(valueTagAttribute, ctx, String.class));
	}

}
