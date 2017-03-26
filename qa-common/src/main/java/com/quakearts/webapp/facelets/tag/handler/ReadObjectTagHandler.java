package com.quakearts.webapp.facelets.tag.handler;

import java.io.Serializable;

import com.quakearts.webapp.facelets.tag.AbstractHibernateHandler;
import com.quakearts.webapp.facelets.tag.HibernateListener;
import com.quakearts.webapp.facelets.tag.listener.ReadObjectListener;

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class ReadObjectTagHandler extends AbstractHibernateHandler {

	private TagAttribute messageAttribute = getAttribute("message"),
			objectAttribute = getRequiredAttribute("object"),
			typeAttribute = getRequiredAttribute("type"),
			idAttribute = getRequiredAttribute("id");
	
	public ReadObjectTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected HibernateListener getHibernateListener(FaceletContext ctx) {
		return new ReadObjectListener(getValueExpression(messageAttribute, ctx, String.class),
				getValueExpression(objectAttribute, ctx, Object.class),
				getValueExpression(idAttribute, ctx, Serializable.class),
				getValueExpression(typeAttribute, ctx, Class.class));
	}

}
