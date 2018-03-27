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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;
import static com.quakearts.webapp.facelets.bootstrap.handlers.BootBaseHandler.*;

public class BootHeaderRenderer extends HtmlBasicRenderer {
	
	private static final String BOOTSTRAP_HEADER = "com.quakearts.bootstrap.header";
	private static String METAINFO = "\r\n<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\" />\r\n" 
				+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\" />\r\n";
	
	private static String DEFAULTBOOTCSS = "<link href=\"@root/boot-services/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
			+"<link href=\"@root/boot-services/css/bootstrap-theme.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n";
	private static String CERULEANCSS = "<link href=\"@root/boot-services/css/bootstrap.cerulean.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n";
	private static String CYBORGCSS = "<link href=\"@root/boot-services/css/bootstrap.cyborg.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n";
	private static String PAPERCSS = "<link href=\"@root/boot-services/css/bootstrap.paper.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n";
	private static String SANDSTONECSS = "<link href=\"@root/boot-services/css/bootstrap.sandstone.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n";
	private static String SUPERHEROCSS = "<link href=\"@root/boot-services/css/bootstrap.superhero.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n";
	private static String HEADERCSS = "<link href=\"@root/boot-services/css/qaboot.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
				+"<link href=\"@root/boot-services/css/management/flaticon.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
				+"<link href=\"@root/boot-services/css/office/flaticon.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
				+"<link href=\"@root/boot-services/css/qacollection/flaticon.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
				+"<link href=\"@root/boot-services/fontawesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n";
	
	public static enum Theme {
		Default(DEFAULTBOOTCSS),
		Cerulean(CERULEANCSS),
		Cyborg(CYBORGCSS),
		Paper(PAPERCSS),
		Sandstone(SANDSTONECSS),
		Superhero(SUPERHEROCSS);
		
		private String link;
		
		private Theme(String link) {
			this.link = link;
		}
		
		public String getLink() {
			return link;
		}
	}
	
	private static final Map<String, Theme> contextThemeMap = new ConcurrentHashMap<>();
	
	private Theme getTheme(FacesContext context) {
		Theme theme = contextThemeMap.get(context.getExternalContext().getRequestContextPath());
		if(theme == null) {
			theme = Theme.Default;
			String themeName = context.getExternalContext().getInitParameter("com.quakearts.bootstrap.theme");
			if(themeName != null)
				try {
					theme = Theme.valueOf(themeName);
				} catch (IllegalArgumentException e) {
				}
			contextThemeMap.put(context.getExternalContext().getRequestContextPath(), theme);
		}
		
		return theme;
	}
	
	public static void setTheme(FacesContext context, Theme theme) {
		contextThemeMap.put(context.getExternalContext().getRequestContextPath(), theme);
	}
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		if(!(component instanceof BootHeaderComponent))
			throw new IOException("Component must be of type "+BootHeaderComponent.class.getName());

		if(context.getAttributes().get(BOOTSTRAP_HEADER)==null){
			UIComponent topComponent = component.getFacet(POSITION_TOP);
			if(topComponent !=null)
				topComponent.encodeAll(context);
			
			if(isBootstrapEnabled()){
				String contextPath = context.getExternalContext().getRequestContextPath();
				ResponseWriter writer = context.getResponseWriter();				
				writer.write(METAINFO);				
				writer.write(new StringBuilder(getTheme(context).getLink())
						.append(HEADERCSS).toString().replaceAll("@root", contextPath));
			}
			context.getAttributes().put(BOOTSTRAP_HEADER,Boolean.TRUE);
			
			UIComponent bottomComponent = component.getFacet(POSITION_BOTTOM);
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
