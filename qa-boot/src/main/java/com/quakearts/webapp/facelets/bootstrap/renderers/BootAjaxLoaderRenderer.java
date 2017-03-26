package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.quakearts.webapp.facelets.bootstrap.components.BootAjaxLoaderComponent;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootAjaxLoaderRenderer extends HtmlBasicRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
	}
	
	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {
	}
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		if(context==null)
			throw new NullPointerException();
		
		if(!(component instanceof BootAjaxLoaderComponent))
			throw new IOException("Component must be of type "+BootAjaxLoaderComponent.class.getName());
		
		BootAjaxLoaderComponent loaderComponent = (BootAjaxLoaderComponent) component;
		
		String mainimagestyle = loaderComponent.get("mainimagestyle");

		ResponseWriter writer = context.getResponseWriter();
		
		writer.write("\n");
		writer.startElement("div", component);
		writer.writeAttribute("id",component.getClientId(context), null);
		writer.writeAttribute("class", "collapse", null);
		writer.write("\n\t");
		writer.startElement("img", component);
		writer.writeAttribute("src", loaderComponent.get("mainloaderimage"), null);
		writer.writeAttribute("border", "0", null);
		if(mainimagestyle!=null)
			writer.writeAttribute("style", mainimagestyle, null);
		
		writer.endElement("img");
		writer.write("\n");
		writer.endElement("div");
		writer.write("\n");
	}
	
	@Override
	public boolean getRendersChildren() {
		return true;
	}
}
