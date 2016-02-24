package com.quakearts.webapp.facelets.bootstrap.handlers;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;

import com.quakearts.webapp.facelets.bootstrap.behaviour.AutoCompleteBehavior;
import com.quakearts.webapp.facelets.bootstrap.behaviour.AutoCompleteListener;
import com.quakearts.webapp.facelets.bootstrap.components.BootSelectOneInput;
import com.quakearts.webapp.facelets.bootstrap.components.BootSelectInputGroup;
import com.quakearts.webapp.facelets.bootstrap.components.BootSelectManyMenu;
import com.quakearts.webapp.facelets.bootstrap.components.BootSelectOneMenu;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class BootSelectMenuHandler extends BootBaseHandler {

	private TagAttribute listenerAttribute = getAttribute("listener");
	
	public BootSelectMenuHandler(ComponentConfig config) {
		super(config);
	}

	@Override
	public void onComponentCreated(FaceletContext ctx, UIComponent c, UIComponent parent) {
		if(!(c instanceof BootSelectInputGroup 
				|| c instanceof BootSelectManyMenu
				|| c instanceof BootSelectOneMenu
				|| c instanceof BootSelectOneInput))
			throw new AbortProcessingException("Component must be of type "+BootSelectInputGroup.class.getName());
		
		ValueExpression expression = c.getValueExpression("autocomplete");
		boolean autocomplete;
		if(expression !=null)
			autocomplete = expression!=null && ObjectExtractor.extractBoolean(expression, ctx);
		else
			try {
				autocomplete = Boolean.parseBoolean((String)c.getAttributes().get("autocomplete"));
			} catch (Exception e) {
				autocomplete = false;
			}
		
		if(autocomplete){
			MethodExpression methodExpression =  listenerAttribute.getMethodExpression(ctx, Object.class, new Class<?>[]{AjaxBehaviorEvent.class});
			if(methodExpression==null)
				throw new AbortProcessingException("Attribute listener is required for autocomplete functionality");				
			
			AutoCompleteBehavior behavior = new AutoCompleteBehavior();
			
			behavior.addAjaxBehaviour(new AutoCompleteListener(methodExpression));
			
			String event = "keyup";
			behavior.setEvent(event);		
			((ClientBehaviorHolder)c).addClientBehavior(event, behavior);
		}
	}
}
