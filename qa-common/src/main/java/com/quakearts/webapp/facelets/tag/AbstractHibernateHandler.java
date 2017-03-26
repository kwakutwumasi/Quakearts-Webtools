package com.quakearts.webapp.facelets.tag;

import javax.faces.event.ActionListener;

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public abstract class AbstractHibernateHandler extends AbstractHandler {

	private TagAttribute domainAttribute = getAttribute("domain");
	
	public AbstractHibernateHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected ActionListener getActionListener(FaceletContext ctx) {
		HibernateListener listener = getHibernateListener(ctx);

		if(domainAttribute != null){
			listener.setDomainExpression(getValueExpression(domainAttribute, ctx, Object.class));
		}

		return listener;
	}
	
	protected abstract HibernateListener getHibernateListener(FaceletContext ctx);

}
