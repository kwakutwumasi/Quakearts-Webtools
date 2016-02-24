package com.quakearts.webapp.facelets.bootstrap.renderers;

import java.io.IOException;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.quakearts.webapp.facelets.bootstrap.components.BootResponsiveImage;
import com.quakearts.webapp.facelets.bootstrap.components.BootResponsiveImage.ImageEntry;
import com.quakearts.webapp.facelets.bootstrap.renderkit.Attribute;
import com.quakearts.webapp.facelets.bootstrap.renderkit.AttributeManager;
import com.quakearts.webapp.facelets.bootstrap.renderkit.html_basic.HtmlBasicRenderer;
import static com.quakearts.webapp.facelets.bootstrap.renderkit.RenderKitUtils.*;
import static com.quakearts.webapp.facelets.bootstrap.common.BootOnLoadComponent.*;

public class BootResponsiveImageRenderer extends HtmlBasicRenderer {
	private static final String RESIZEWRITTEN = "com.quakearts.boostrap.RESIZEWRITTEN";
	private static final Attribute[] ATTRIBUTES = AttributeManager.getAttributes(AttributeManager.Key.RESPONSIVEIMAGE);
	
	@Override
	protected void getEndTextToRender(FacesContext context, UIComponent component, String currentValue)
			throws IOException {
		if(!(component instanceof BootResponsiveImage))
			throw new IOException("Compoenent must be of type "+BootResponsiveImage.class.getName());
		
		BootResponsiveImage image = (BootResponsiveImage)component;
		List<BootResponsiveImage.ImageEntry> entries = image.getImageEntries();
		
		if(entries.isEmpty())
			return;
		
		String styleClass = image.get("styleClass");
		
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("img", component);
		writer.writeAttribute("id", component.getClientId(context), "id");
		writer.writeAttribute("src", entries.get(entries.size()-1).getImagePath(), null);
		if(styleClass!=null)
		renderPassThruAttributes(context, writer, component, ATTRIBUTES);
		writer.endElement("img");
		writer.write("\n");
		
		if(!context.getAttributes().containsKey(RESIZEWRITTEN)){
			context.getAttributes().put(RESIZEWRITTEN,"");
			addScriptContent("\t$(window).resize(qab.rsi);\n", context);
		}
		
		StringBuilder builder = new StringBuilder("\tqab.rsel.push({\"resizeImage\":function(windowWidth){\n\t\tvar src = \"\";\n");

		for(int i=0; i<entries.size(); i++) {
			ImageEntry entry = entries.get(i);
			builder.append("\t\t");
			if(i>0)
				builder.append("else ");
			
			if(i!=entries.size()-1)
			builder.append("if(windowWidth<=")
				.append(entry.getSize()).append(")");
			builder.append("\n");			
			builder.append("\t\t\tsrc=\"")
				.append(entry.getImagePath())
				.append("?id=\"+new Date().getTime();\n");
		}
		
		builder.append("\t\t$(\"#")
			.append(component.getClientId(context).replace(":", "\\\\:"))
			.append("\").attr(\"src\",src);\n\t}});\n")
			.append("\tqab.rsel[qab.rsel.length-1].resizeImage($(window).width());\n");
		
		addScriptContent(builder.toString(), context);
	}
}
