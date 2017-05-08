/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.bootstrap.handlers;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import javax.faces.view.facelets.TagHandler;
import com.quakearts.webapp.facelets.bootstrap.common.BootHeaderComponent;
import com.quakearts.webapp.facelets.util.ObjectExtractor;
import static com.quakearts.webapp.facelets.bootstrap.common.BootHeaderComponent.*;

public class BootResourceTagHandler extends TagHandler {

	private static final String CSS = "css";
	private static final String SCRIPT = "script";
	
	private TagAttribute nameAttribute = getRequiredAttribute("name"),
			libraryAttribute = getRequiredAttribute("library"),
			positionAttribute = getAttribute("position"), 
			typeAttribute = getRequiredAttribute("type");
	
	public BootResourceTagHandler(TagConfig config) {
		super(config);
	}

	@Override
	public void apply(FaceletContext ctx, UIComponent parent)
			throws IOException {
		if(ctx.getFacesContext().getPartialViewContext().isPartialRequest())
			return;
			
		if(!parent.getRendererType().equals("javax.faces.Head"))
			throw new IOException("Component must be nested within a javax.faces.Head buffer component");
		
        addBootComponentToHead(ctx.getFacesContext());
		String position = POSITION_BOTTOM;
		if(positionAttribute!=null){
			position = ObjectExtractor.extractString(positionAttribute.getValueExpression(ctx, String.class),ctx);
			if(!POSITION_TOP.equals(position)||!POSITION_BOTTOM.equals(position))
				position = POSITION_BOTTOM;
		}
		String name = ObjectExtractor.extractString(nameAttribute.getValueExpression(ctx, String.class), ctx);
		String library = ObjectExtractor.extractString(libraryAttribute.getValueExpression(ctx, String.class), ctx);
		String type = ObjectExtractor.extractString(typeAttribute.getValueExpression(ctx, String.class), ctx);
		
		if(!CSS.equals(type)&&!SCRIPT.equals(type))
			throw new IOException("Attribute type must be one of script or css");
		
		BootHeaderComponent component = (BootHeaderComponent) ctx
				.getFacesContext().getAttributes()
				.get(BOOT_LIBRARY_LOADED);
		
		UIOutput resource = new UIOutput();
    	resource.getAttributes().put("name", name);
    	resource.getAttributes().put("library", library);
    	resource.setRendererType(type.equals(SCRIPT)?"javax.faces.resource.Script":"javax.faces.resource.Stylesheet");

    	if(position.equals(POSITION_TOP)){
        	component.addToTop(resource);
		} else {
        	component.addToBottom(resource);
		}
	}

}
