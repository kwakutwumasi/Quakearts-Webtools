package com.quakearts.webapp.facelets.tag.handler;

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

import com.quakearts.webapp.facelets.tag.AbstractHibernateHandler;
import com.quakearts.webapp.facelets.tag.HibernateListener;
import com.quakearts.webapp.facelets.tag.listener.DeleteObjectListener;

public class DeleteObjectTagHandler extends AbstractHibernateHandler {

	private TagAttribute messageAttribute = getAttribute("message"),
	objectAttribute = getRequiredAttribute("object");
	
	public DeleteObjectTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected HibernateListener getHibernateListener(FaceletContext ctx) {
		return new DeleteObjectListener(getValueExpression(messageAttribute, ctx, String.class),
				getValueExpression(objectAttribute, ctx, Object.class));
	}

}
