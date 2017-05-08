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

import com.quakearts.webapp.facelets.bootstrap.components.BootAjaxLoaderComponent;
import com.quakearts.webapp.facelets.bootstrap.components.BootAjaxLoaderScriptComponent;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootAjaxLoaderScriptRenderer extends HtmlBasicRenderer {
	private static final String LOADERSCRIPT = "qab.adid =\"$ajaxdiv\";\r\n"
			+ "qab.sd=$stime;\r\n"
			+ "qab.ed=$etime;\r\n"
			+ "qab.ovlimg=\"$overlayimg\";\r\n"
			+ "qab.ovlimgcss=\"$overlayimagestyle\";\r\n"
			+ "qab.miniimg=\"$miniimg\";\r\n"
			+ "qab.miniimgcss=\"$miniimagestyle\";";
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
	}
	
	public void encodeChildren(FacesContext context, UIComponent component) throws IOException {	
	};
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException{
		if(context==null)
			throw new NullPointerException();
		
		if(!(component instanceof BootAjaxLoaderScriptComponent))
			throw new IOException("Component must be of type "+BootAjaxLoaderScriptComponent.class.getName());
		
		BootAjaxLoaderComponent loaderComponent = ((BootAjaxLoaderScriptComponent)component).getLoaderComponent();
		if(loaderComponent!=null){
			ResponseWriter writer = context.getResponseWriter();
			writer.startElement("script", component);
			
			String miniimagestyle = loaderComponent.get("miniimagestyle");
			String startTimeout = loaderComponent.get("startTimeout");
			String endTimeout = loaderComponent.get("endTimeout");
			String overlayimagestyle = loaderComponent.get("overlayimagestyle");
			
			writer.write(LOADERSCRIPT.replace("$ajaxdiv", loaderComponent.getClientId(context))
					.replace("$miniimg", loaderComponent.get("miniloaderimage"))
					.replace("$miniimagestyle", miniimagestyle!=null?("style=\\\""+miniimagestyle+"\\\" "):"")
					.replace("$overlayimg", loaderComponent.get("overlayloaderimage"))
					.replace("$overlayimagestyle", overlayimagestyle!=null?("style=\\\""+overlayimagestyle+"\\\" "):"")
					.replace("$stime", startTimeout)
					.replace("$etime", endTimeout));
			writer.endElement("script");
		}
	}
		
	@Override
	public boolean getRendersChildren() {
		return true;
	}

}
