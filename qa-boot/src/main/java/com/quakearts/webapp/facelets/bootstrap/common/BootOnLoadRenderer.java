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

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;
import static com.quakearts.webapp.facelets.bootstrap.common.BootOnLoadComponent.*;
import static com.quakearts.webapp.facelets.bootstrap.handlers.BootBaseHandler.*;

public class BootOnLoadRenderer extends HtmlBasicRenderer {
	private static final String ROOT = "@root";

	private static final String BOOTSTRAP_ONLOAD = "com.quakearts.bootstrap.onload";

	public static final String RESPOND = "<script src=\"@root/boot-services/js/respond.min.js\" type=\"text/javascript\"></script>\r\n";
	public static final String JQUERY = "<script src=\"@root/boot-services/js/jquery-1.12.4.min.js\" type=\"text/javascript\"></script>\r\n";
	public static final String JQUERYDEBUG = "<script src=\"@root/boot-services/js/jquery-1.12.4.js\" type=\"text/javascript\"></script>\r\n";
	public static final String JS = "<script src=\"@root/boot-services/js/bootstrap.min.js\" type=\"text/javascript\"></script>\r\n"+
									"<script src=\"@root/boot-services/js/qaboot.min.js\" type=\"text/javascript\"></script>\r\n";
	public static final String JSDEBUG = "<script src=\"@root/boot-services/js/bootstrap.js\" type=\"text/javascript\"></script>\r\n"+
			"<script src=\"@root/boot-services/js/qaboot.js\" type=\"text/javascript\"></script>\r\n";

	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws java.io.IOException {
		if(context.getPartialViewContext().isPartialRequest() && !context.getPartialViewContext().getRenderIds().contains("javax.faces.ViewRoot")
					&& !context.getPartialViewContext().getRenderIds().contains("@all")) {
			return;
		}
		
		if(context.getAttributes().get(BOOTSTRAP_ONLOAD)==null){
			ResponseWriter writer = context.getResponseWriter();
			String contextPath = context.getExternalContext().getRequestContextPath();
			renderTopResources(context, component);
			renderJqueryAndBootstrap(writer, contextPath);
			renderBottomResources(context, component);
			renderScriptContent(context, component, writer);
			context.getAttributes().put(BOOTSTRAP_ONLOAD, Boolean.TRUE);
		}
	}

	private void renderTopResources(FacesContext context, UIComponent component) throws IOException {
		UIComponent topComponent = component.getFacet(POSITION_TOP);
		if(topComponent !=null)
			topComponent.encodeAll(context);
	}

	private void renderJqueryAndBootstrap(ResponseWriter writer, String contextPath) throws IOException {
		if(isBootstrapEnabled()){
			if(isRespondEnabled())
				writer.write(RESPOND.replaceAll(ROOT, contextPath));
			if(isjQueryEnabled())
				writer.write((isInJSDebugMode()?JQUERYDEBUG:JQUERY).replaceAll(ROOT, contextPath));

			writer.write((isInJSDebugMode()? JSDEBUG:JS).replaceAll(ROOT, contextPath));
		}
	}

	private void renderBottomResources(FacesContext context, UIComponent component) throws IOException {
		UIComponent bottomComponent = component.getFacet(POSITION_BOTTOM);
		if(bottomComponent !=null)
			bottomComponent.encodeAll(context);
	}

	@SuppressWarnings("unchecked")
	private void renderScriptContent(FacesContext context, UIComponent component, ResponseWriter writer)
			throws IOException {
		List<Function<FacesContext, String>> scriptContentList = (List<Function<FacesContext, String>>) context.getAttributes().get(SCRIPTCONTENTLIST);
		if(scriptContentList!=null){
			writer.startElement("script", component);				
			writer.write("\n");				
			for(Function<FacesContext, String> scriptContentSupplier:scriptContentList)
				writer.write(scriptContentSupplier.apply(context));
			writer.write("\n");
			writer.endElement("script");
		}
	}
}
