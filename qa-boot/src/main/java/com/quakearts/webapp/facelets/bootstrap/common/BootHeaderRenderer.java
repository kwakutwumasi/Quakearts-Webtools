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

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;
import static com.quakearts.webapp.facelets.bootstrap.common.BootHeaderComponent.*;

public class BootHeaderRenderer extends HtmlBasicRenderer {
	
	private static final String BOOTSTRAP_HEADER = "com.quakearts.bootstrap.header";
	public static String METAINFO = "\r\n<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />\r\n" 
				+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\" />\r\n";
	public static String HEADERCSS = "<link href=\"@root/boot-services/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
				+"<link href=\"@root/boot-services/css/bootstrap-theme.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
				+"<link href=\"@root/boot-services/css/qaboot.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
				+"<link href=\"@root/boot-services/css/management/flaticon.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
				+"<link href=\"@root/boot-services/css/office/flaticon.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
				+"<link href=\"@root/boot-services/css/qacollection/flaticon.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
				+"<link href=\"@root/boot-services/fontawesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n";
	public static String HEADERRESPOND = "<script src=\"@root/boot-services/js/respond.min.js\" type=\"text/javascript\"></script>\r\n";
	public static String HEADERJQUERY = "<script src=\"@root/boot-services/js/jquery-1.12.2.min.js\" type=\"text/javascript\"></script>\r\n";
	public static String HEADERJQUERYDEBUG = "<script src=\"@root/boot-services/js/jquery-1.12.2.js\" type=\"text/javascript\"></script>\r\n";
	public static String HEADERJS = "<script src=\"@root/boot-services/js/bootstrap.min.js\" type=\"text/javascript\"></script>\r\n"+
									"<script src=\"@root/boot-services/js/qaboot.min.js\" type=\"text/javascript\"></script>\r\n";
	public static String HEADERJSDEBUG = "<script src=\"@root/boot-services/js/bootstrap.js\" type=\"text/javascript\"></script>\r\n"+
			"<script src=\"@root/boot-services/js/qaboot.js\" type=\"text/javascript\"></script>\r\n";
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		if(!(component instanceof BootHeaderComponent))
			throw new IOException("Component must be of type "+BootHeaderComponent.class.getName());

		if(context.getAttributes().get(BOOTSTRAP_HEADER)==null){
			UIComponent topComponent = component.getFacet(BootHeaderComponent.POSITION_TOP);
			if(topComponent !=null)
				topComponent.encodeAll(context);
			
			if(isBootstrapEnabled()){
				String contextPath = context.getExternalContext().getRequestContextPath();
				ResponseWriter writer = context.getResponseWriter();
				writer.write(HEADERCSS.replaceAll("@root", contextPath));
				writer.write(METAINFO);
				if(isRespondEnabled())
					writer.write(HEADERRESPOND.replaceAll("@root", contextPath));
				if(isjQueryEnabled())
					writer.write((isInJSDebugMode()?HEADERJQUERYDEBUG:HEADERJQUERY).replaceAll("@root", contextPath));

				writer.write((isInJSDebugMode()? HEADERJSDEBUG:HEADERJS).replaceAll("@root", contextPath));
			}
			context.getAttributes().put(BOOTSTRAP_HEADER,Boolean.TRUE);
			
			UIComponent bottomComponent = component.getFacet(BootHeaderComponent.POSITION_BOTTOM);
			if(bottomComponent !=null)
				bottomComponent.encodeAll(context);
		}
	}
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
	}
	
	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {
	}
		
    @Override
    public boolean getRendersChildren()
    {
        return false;
    }

}
