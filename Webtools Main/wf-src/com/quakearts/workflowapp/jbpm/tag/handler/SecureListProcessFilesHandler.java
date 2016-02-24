package com.quakearts.workflowapp.jbpm.tag.handler;

import com.quakearts.workflowapp.jbpm.tag.listener.SecureListProcessFilesListener;
import javax.faces.view.facelets.FaceletContext;

import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;


import java.util.Collection;

import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.core.handler.AbstractHandler;

public class SecureListProcessFilesHandler extends AbstractHandler {
    private final TagAttribute idAttribute = getRequiredAttribute("id");
    private final TagAttribute targetAttribute = getRequiredAttribute("target");
    
    public SecureListProcessFilesHandler(TagConfig config) {
        super(config);
    }

    protected JbpmActionListener getListener(FaceletContext ctx) {
        return new SecureListProcessFilesListener(getValueExpression(idAttribute,ctx,Long.class),getValueExpression(targetAttribute,ctx,Collection.class));
    }
}
