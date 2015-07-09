package com.quakearts.webapp.facelets.tag.handler;

import com.quakearts.webapp.facelets.tag.AbstractHibernateHandler;
import com.quakearts.webapp.facelets.tag.HibernateListener;
import com.quakearts.webapp.facelets.tag.listener.MergeObjectListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class MergeObjectTagHandler extends AbstractHibernateHandler {

	private TagAttribute objectAttribute = getRequiredAttribute("object"), deleteAttribute = getAttribute("delete"),
	messageAttribute=getAttribute("message");

	public MergeObjectTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected HibernateListener getHibernateListener(FaceletContext ctx) {
		return new MergeObjectListener(getValueExpression(objectAttribute, ctx, Object.class),
				getValueExpression(deleteAttribute, ctx, Object.class),
				getValueExpression(messageAttribute, ctx, String.class));
	}

}
