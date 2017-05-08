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
package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.bootstrap.components.BootLabel;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootLabelRenderer extends HtmlBasicRenderer {
	
	@Override
	protected void getEndTextToRender(FacesContext context,
			UIComponent component, String currentValue) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		if(!(component instanceof BootLabel)){
			throw new IOException("Component must be of type "+BootLabel.class.getName());
		}
		
		BootLabel label = ((BootLabel)component);
		String type = label.get("type");
		String style = label.get("style");
		String styleClass = label.get("styleClass");
		
		writer.startElement("span", component);
		writeIdAttributeIfNecessary(context, writer, component);
		writer.writeAttribute("class", "label label-"+type+(styleClass!=null?" "+styleClass:""), null);
		if(style!=null)
			writer.writeAttribute("style", style, null);
		writer.write(currentValue);
		writer.endElement("span");
	}
	
	public boolean getRendersChildren() {
		return true;
	}
	
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
		//Do nothing
	};
}
