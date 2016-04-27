package org.jboss.gravel.action.handler;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.PhaseId;
import javax.faces.event.PreRenderViewEvent;

import org.jboss.gravel.action.action.ResponseActionSystemListener;
import org.jboss.gravel.action.ui.UIResponseActions;

/**
 *
 */
public final class ResponseActionsHandler extends ComponentHandler {

    public ResponseActionsHandler(final ComponentConfig config) {
        super(config);
    }

    @Override
    public void onComponentPopulated(FaceletContext ctx, UIComponent c,
    		UIComponent parent) {
    	
    	if(ComponentHandler.isNew(c)){
			List<UIResponseActions> components = (List<UIResponseActions>) FacesContext
					.getCurrentInstance().getAttributes()
					.get(UIResponseActions.RESPONSEACTION);
			
			if(components==null){
				ctx.getFacesContext().getAttributes().put(UIResponseActions.RESPONSEACTION, (components = new ArrayList<UIResponseActions>()));
				if(!ctx.getFacesContext().isPostback())
		    		ctx.getFacesContext().getViewRoot().subscribeToEvent(PreRenderViewEvent.class, new ResponseActionSystemListener());
			}
			
			components.add((UIResponseActions)c);
    	}
    }
}
