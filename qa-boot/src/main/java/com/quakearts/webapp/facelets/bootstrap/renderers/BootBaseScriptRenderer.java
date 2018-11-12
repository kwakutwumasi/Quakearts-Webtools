package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.CharArrayWriter;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.quakearts.webapp.facelets.bootstrap.common.BootOnLoadComponent;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;

public class BootBaseScriptRenderer extends HtmlBasicRenderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
	}
	
	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {
		if(context==null)
			throw new NullPointerException();
		ResponseWriter oldWriter = context.getResponseWriter();
		CharArrayWriter writer = new CharArrayWriter();
		ResponseWriter newWriter = oldWriter.cloneWithWriter(writer);
		context.setResponseWriter(newWriter);
		super.encodeChildren(context, component);
		String content = writer.toString();
		BootOnLoadComponent.addScriptContent(fctx->content, context);
		context.setResponseWriter(oldWriter);
	}
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
	}
	
	@Override
	public boolean getRendersChildren() {
		return true;
	}
}
