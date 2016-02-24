package com.quakearts.workflowapp.jbpm.tag.handler;

import com.quakearts.workflowapp.jbpm.tag.listener.SecureNextTaskIdListener;
import javax.faces.view.facelets.FaceletContext;

import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;


import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.core.handler.AbstractHandler;

public class SecureNextTaskIdHandler extends AbstractHandler {
    private final TagAttribute idTagAttribute = getRequiredAttribute("id");
    private final TagAttribute tragetTagAttribute = getRequiredAttribute("target");
    public SecureNextTaskIdHandler(TagConfig config) {
        super(config);
    }

    protected JbpmActionListener getListener(FaceletContext ctx) {
        return new SecureNextTaskIdListener(getValueExpression(idTagAttribute,ctx,Long.class),getValueExpression(tragetTagAttribute,ctx,Long.class));
    }
}
