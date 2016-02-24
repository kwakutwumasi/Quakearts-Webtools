package com.quakearts.workflowapp.jbpm.tag.output;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import com.sun.faces.renderkit.html_basic.HtmlBasicRenderer;

public class FileUploadRenderer extends HtmlBasicRenderer {

    public static final String RENDERER_TYPE = "com.quakearts.facelets.output.fileupload.renderer";

	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		if(context == null)
        {
            throw new NullPointerException("Context is null");
        }
        if(component == null)
        {
            throw new NullPointerException("Component is null");
        } else
        {
            return;
        }
	}

	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		ResponseWriter writer = null;
		
		if(context == null)
        {
            throw new NullPointerException("Context is null");
        }
        if(!(component instanceof FileUploadComponent))
        {
            throw new NullPointerException("Component is not an instance of FileDownloadComponent");
        }        
        if(!component.isRendered())
        {
            return;
        }
        writer = context.getResponseWriter();
        
        FileUploadComponent fileUC = (FileUploadComponent) component;
        
        writer.startElement("input", null);
		writer.writeAttribute("type", "button", null);
		writer.writeAttribute("value", "Attach a file....", null);
		writer.writeAttribute("onclick", "javascript:window.open('"+fileUC.getUploadUrl()+"?id="+fileUC.getTid()+"&amp;var="+fileUC.getVar()+"','','directories=no,height=300px,location=no,menubar=no,resizable=yes,scrollbars=no,status=yes,titlebar=no,toolbar=no,width="+fileUC.getWidth()+"');return false;", null);
		writer.endElement("input");        
	}

}
