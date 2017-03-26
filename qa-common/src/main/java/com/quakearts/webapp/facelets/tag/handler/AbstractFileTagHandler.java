package com.quakearts.webapp.facelets.tag.handler;

import javax.el.ValueExpression;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

import com.quakearts.webapp.facelets.tag.AbstractHandler;

public abstract class AbstractFileTagHandler extends AbstractHandler {
	protected TagAttribute rootAttribute = getAttribute("root");
	
	public AbstractFileTagHandler(TagConfig config) {
		super(config);
	}

	protected ValueExpression getRoot(FaceletContext ctx){
		return getValueExpression(rootAttribute, ctx, String.class);
	}
	
}
