package com.quakearts.webapp.facelets.tag;

import java.io.IOException;

import javax.el.ELException;
import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.ActionSource;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.FaceletException;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;

import com.quakearts.webapp.facelets.ComponentSupport;

public abstract class AbstractHandler extends TagHandler {

	private TagAttribute unless = getAttribute("unless");

	public AbstractHandler(TagConfig config) {
		super(config);
	}

	@Override
	public void apply(FaceletContext ctx, UIComponent parent)
			throws IOException, FacesException, FaceletException, ELException {
		if(ComponentSupport.isNew(parent)){
			if(parent instanceof ActionSource){
					ActionListener listener = getActionListener(ctx);
					if(listener instanceof BaseListener)
						((BaseListener)listener).setUnlessExpression(getValueExpression(unless, ctx, Boolean.class));
					
					((ActionSource)parent).addActionListener(listener);
			}else{
				throw new FacesException("Component must be subclass of javax.faces.component.ActionSource");
			}
		}
		
	}

    protected abstract ActionListener getActionListener(FaceletContext ctx);

	protected ValueExpression getValueExpression(TagAttribute tagAttribute, FaceletContext ctx, Class<?> type) {
        return tagAttribute == null ? null : tagAttribute.getValueExpression(ctx, type);
    }

}
