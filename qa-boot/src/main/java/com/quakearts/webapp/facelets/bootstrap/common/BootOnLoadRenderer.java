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
package com.quakearts.webapp.facelets.bootstrap.common;

import java.util.List;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;
import static com.quakearts.webapp.facelets.bootstrap.common.BootOnLoadComponent.*;

public class BootOnLoadRenderer extends HtmlBasicRenderer {
	@SuppressWarnings("unchecked")
	public void encodeEnd(javax.faces.context.FacesContext context, javax.faces.component.UIComponent component) throws java.io.IOException {
		List<String> scriptContentList = (List<String>) context.getAttributes().get(SCRIPTCONTENTLIST);
		if(scriptContentList!=null){
			ResponseWriter writer = context.getResponseWriter();
			writer.startElement("script", component);
			writer.write("//");
			writer.startCDATA();
			writer.write("\n");
			writer.write("$(function() {\n");
			for(String scriptContent:scriptContentList)
				writer.write(scriptContent);
			
			writer.write("});\n");
			writer.write("\n");
			writer.write("//");
			writer.endCDATA();
			writer.endElement("script");
		}
	}
}
