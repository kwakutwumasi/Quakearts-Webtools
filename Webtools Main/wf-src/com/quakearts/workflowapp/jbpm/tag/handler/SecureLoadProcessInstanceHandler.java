package com.quakearts.workflowapp.jbpm.tag.handler;

import com.quakearts.workflowapp.jbpm.tag.listener.SecureLoadProcessInstanceActionListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;


import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.core.handler.AbstractHandler;

// Referenced classes of package org.jbpm.jsf.core.handler:
//            AbstractHandler

public final class SecureLoadProcessInstanceHandler extends AbstractHandler
{

    private final TagAttribute idTagAttribute = getRequiredAttribute("id");
    private final TagAttribute targetTagAttribute = getRequiredAttribute("target");
    private final TagAttribute forUpdateTagAttribute = getAttribute("forUpdate");

    public SecureLoadProcessInstanceHandler(TagConfig config)
    {
        super(config);
    }

    protected JbpmActionListener getListener(FaceletContext ctx)
    {
        return new SecureLoadProcessInstanceActionListener(getValueExpression(idTagAttribute, ctx, Long.class), getValueExpression(targetTagAttribute, ctx, ProcessInstance.class), getValueExpression(forUpdateTagAttribute, ctx, Boolean.class));
    }
}
