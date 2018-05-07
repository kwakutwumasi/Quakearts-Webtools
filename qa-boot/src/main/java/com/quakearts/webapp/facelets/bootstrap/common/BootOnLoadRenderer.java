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
package com.quakearts.webapp.facelets.bootstrap.common;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;
import static com.quakearts.webapp.facelets.bootstrap.common.BootOnLoadComponent.*;
import static com.quakearts.webapp.facelets.bootstrap.handlers.BootBaseHandler.*;

public class BootOnLoadRenderer extends HtmlBasicRenderer {
	private static final String BOOTSTRAP_ONLOAD = "com.quakearts.bootstrap.onload";

	public static String RESPOND = "<script src=\"@root/boot-services/js/respond.min.js\" type=\"text/javascript\"></script>\r\n";
	public static String JQUERY = "<script src=\"@root/boot-services/js/jquery-1.12.2.min.js\" type=\"text/javascript\"></script>\r\n";
	public static String JQUERYDEBUG = "<script src=\"@root/boot-services/js/jquery-1.12.2.js\" type=\"text/javascript\"></script>\r\n";
	public static String JS = "<script src=\"@root/boot-services/js/bootstrap.min.js\" type=\"text/javascript\"></script>\r\n"+
									"<script src=\"@root/boot-services/js/qaboot.min.js\" type=\"text/javascript\"></script>\r\n";
	public static String JSDEBUG = "<script src=\"@root/boot-services/js/bootstrap.js\" type=\"text/javascript\"></script>\r\n"+
			"<script src=\"@root/boot-services/js/qaboot.js\" type=\"text/javascript\"></script>\r\n";

	
	@SuppressWarnings("unchecked")
	public void encodeEnd(FacesContext context, UIComponent component) throws java.io.IOException {
		//If this is an AJAX request, then this is not necessary no? TODO: investigate
		if(context.getPartialViewContext().isPartialRequest()){
			if(!context.getPartialViewContext().getRenderIds().contains("javax.faces.ViewRoot")
					&& !context.getPartialViewContext().getRenderIds().contains("@all")) {
				return;
			}
		}
		
		if(context.getAttributes().get(BOOTSTRAP_ONLOAD)==null){
			ResponseWriter writer = context.getResponseWriter();
			String contextPath = context.getExternalContext().getRequestContextPath();
			UIComponent topComponent = component.getFacet(POSITION_TOP);
			if(topComponent !=null)
				topComponent.encodeAll(context);

			if(isBootstrapEnabled()){
				if(isRespondEnabled())
					writer.write(RESPOND.replaceAll("@root", contextPath));
				if(isjQueryEnabled())
					writer.write((isInJSDebugMode()?JQUERYDEBUG:JQUERY).replaceAll("@root", contextPath));

				writer.write((isInJSDebugMode()? JSDEBUG:JS).replaceAll("@root", contextPath));
			}

			List<String> scriptContentList = (List<String>) context.getAttributes().get(SCRIPTCONTENTLIST);
			if(scriptContentList!=null){
				writer.startElement("script", component);				
				writer.write("\n");				
				for(String scriptContent:scriptContentList)
					writer.write(scriptContent);
				writer.write("\n");
				writer.endElement("script");
			}
		
			UIComponent bottomComponent = component.getFacet(POSITION_BOTTOM);
			if(bottomComponent !=null)
				bottomComponent.encodeAll(context);

			context.getAttributes().put(BOOTSTRAP_ONLOAD, Boolean.TRUE);
		}
	}
}
