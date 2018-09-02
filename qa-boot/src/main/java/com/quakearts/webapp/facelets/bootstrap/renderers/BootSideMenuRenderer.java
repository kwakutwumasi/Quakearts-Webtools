/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.quakearts.webapp.facelets.bootstrap.components.BootSideMenuComponent;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager.Key;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;
import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;

public class BootSideMenuRenderer extends HtmlBasicRenderer {
	
	private static final Attribute[] ATTRIBUTES = AttributeManager.getAttributes(Key.SIDEMENU);
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
		if(!(component instanceof BootSideMenuComponent))
			throw new IOException("Component must be of type "+BootSideMenuComponent.class.getName());
		
		BootSideMenuComponent sideMenu =(BootSideMenuComponent)component;
		
		boolean disabled = (Boolean.valueOf(sideMenu.get("disabled")));
		
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div", component);
		writeIdAttributeIfNecessary(context, writer, component);
		renderPassThruAttributes(context, writer, component, ATTRIBUTES);
		renderHTML5DataAttributes(context, component);
		String styleClass = sideMenu.get("styleClass");
		writer.writeAttribute("class", "side-menu"+(styleClass!=null?" "+styleClass:"")+(disabled?" disabled":""), null);		
		String style = sideMenu.get("style");
		if(style!=null)
			writer.writeAttribute("style", style, null);
		
		if(!disabled){
			writer.writeAttribute("onmouseenter", "qab.tm(this,true)", null);
			writer.writeAttribute("onmouseleave", "qab.tm(this,false)", null);
		}
		writer.write("\n");
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		context.getResponseWriter().endElement("div");
	}
	 
	@Override
	public boolean getRendersChildren() {
		return true;
	}
	
}
