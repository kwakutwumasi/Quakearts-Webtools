package com.quakearts.webapp.facelets.tag.handler;

import com.quakearts.webapp.facelets.tag.AbstractHibernateHandler;
import com.quakearts.webapp.facelets.tag.HibernateListener;
import com.quakearts.webapp.facelets.tag.listener.SaveOrUpdateObjectListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class SaveOrUpdateObjectTagHandler extends AbstractHibernateHandler {

	private TagAttribute messageAttribute = getAttribute("message"),
	objectAttribute = getRequiredAttribute("object"),
	actionAttribute = getAttribute("action");
	
	public SaveOrUpdateObjectTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected HibernateListener getHibernateListener(FaceletContext ctx) {
		return new SaveOrUpdateObjectListener(getValueExpression(messageAttribute, ctx, String.class),
				getValueExpression(objectAttribute, ctx, Object.class),
				getValueExpression(actionAttribute, ctx, String.class));
	}

}
