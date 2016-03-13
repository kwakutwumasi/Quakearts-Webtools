package com.quakearts.webapp.facelets.bootstrap.handlers;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.MetaRuleset;

import static com.quakearts.webapp.facelets.bootstrap.common.BootHeaderComponent.*;


public class BootBaseHandler extends ComponentHandler {

	public BootBaseHandler(ComponentConfig config) {
		super(config);
	}
	
	@Override
	public void apply(FaceletContext ctx, UIComponent parent)
			throws IOException {
        addBootComponentToHead(ctx.getFacesContext());
        getTagHandlerDelegate().apply(ctx, parent);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected MetaRuleset createMetaRuleset(Class type) {
		return super.createMetaRuleset(type).addRule(HTML5DataRule.Instance);
	}
}
