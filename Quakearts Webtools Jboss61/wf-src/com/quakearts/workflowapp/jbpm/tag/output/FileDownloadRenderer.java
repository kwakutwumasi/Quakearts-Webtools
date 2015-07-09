package com.quakearts.workflowapp.jbpm.tag.output;

import java.io.IOException;
import java.util.Collection;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.quakearts.workflowapp.jbpm.util.process.ProcessFile;
import com.sun.faces.renderkit.html_basic.HtmlBasicRenderer;

public class FileDownloadRenderer extends HtmlBasicRenderer {

    public static final String RENDERER_TYPE = "com.quakearts.facelets.output.filedownload.renderer";

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

	@SuppressWarnings("rawtypes")
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		ResponseWriter writer = null;
        
        if(context == null)
        {
            throw new NullPointerException("Context is null");
        }
        if(!(component instanceof FileDownloadComponent))
        {
            throw new NullPointerException("Component is not an instance of FileDownloadComponent");
        }        
        if(!component.isRendered())
        {
            return;
        }
        writer = context.getResponseWriter();
        
        FileDownloadComponent fileDC = (FileDownloadComponent) component;
        
        Collection fileList = fileDC.getVar();
        for(Object obj:fileList){
        	if(obj instanceof ProcessFile){
        		writer.startElement("input", null);
        		writer.writeAttribute("type", "button", null);
        		writer.writeAttribute("value", "Download/View Attachment....", null);
        		writer.writeAttribute("onclick", "javascript:window.open('"+fileDC.getDownloadUrl()+"?id="+fileDC.getTid()+"&amp;ticket="+((ProcessFile)obj).getTicket()+"','','directories=no,height=300px,location=no,menubar=no,resizable=yes,scrollbars=no,status=yes,titlebar=no,toolbar=no,width="+fileDC.getWidth()+"');return false;", null);
        		writer.endElement("input");
        	}
        }
	}

}
