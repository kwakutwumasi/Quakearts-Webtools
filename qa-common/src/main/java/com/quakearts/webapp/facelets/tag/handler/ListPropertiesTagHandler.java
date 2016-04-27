package com.quakearts.webapp.facelets.tag.handler;

import java.util.Collection;
import java.util.Properties;

import javax.faces.event.ActionListener;

import com.quakearts.webapp.facelets.tag.AbstractHandler;
import com.quakearts.webapp.facelets.tag.listener.ListPropertiesListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class ListPropertiesTagHandler extends AbstractHandler {

	private TagAttribute propertiesAttribute = getRequiredAttribute("properties");
	private TagAttribute varTagAttribute = getRequiredAttribute("var");

	public ListPropertiesTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		return new ListPropertiesListener(getValueExpression(propertiesAttribute, ctx, Properties.class),
				getValueExpression(varTagAttribute, ctx, Collection.class));
	}

}
