package com.quakearts.webapp.facelets.bootstrap.handlers;

import java.io.IOException;
import java.util.function.Supplier;

import javax.faces.component.UIComponent;
import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TextHandler;

import com.quakearts.webapp.facelets.bootstrap.components.BootOutputComponent;

import static com.quakearts.webapp.facelets.bootstrap.handlers.BootBaseHandler.*;

public class BootOutputHandler extends ComponentHandler {

	public BootOutputHandler(ComponentConfig config) {
		super(config);
	}

	@Override
	public void apply(FaceletContext ctx, UIComponent parent) throws IOException {
        addBootComponent(ctx.getFacesContext());
        if(!(nextHandler instanceof TextHandler)){
        	throw new IOException("Only text content allowed in output tag");
        }
		if(!getComponentConfig().getComponentType().equals("com.quakearts.bootstrap.output")){
        	throw new IOException("componentType must be com.quakearts.bootstrap.output");
		}
        getTagHandlerDelegate().apply(ctx, parent);
	}

	@Override
	public void onComponentCreated(FaceletContext ctx, UIComponent c, UIComponent parent) {
		BootOutputComponent outputComponent = (BootOutputComponent) c;
		Supplier<String> supplier = ()->((TextHandler)nextHandler).getText(ctx);
		outputComponent.getAttributes().put(BootOutputComponent.VALUE, supplier);
	}
}
