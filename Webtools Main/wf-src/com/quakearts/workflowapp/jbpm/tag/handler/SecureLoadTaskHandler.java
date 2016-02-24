package com.quakearts.workflowapp.jbpm.tag.handler;

import com.quakearts.workflowapp.jbpm.tag.listener.SecureLoadTasksActionListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.core.handler.AbstractHandler;

import org.jbpm.taskmgmt.exe.TaskInstance;
// Referenced classes of package org.jbpm.jsf.core.handler:
//            AbstractHandler

public final class SecureLoadTaskHandler extends AbstractHandler
{

    private final TagAttribute idTagAttribute = getRequiredAttribute("id");
    private final TagAttribute targetTagAttribute = getRequiredAttribute("target");
    private final TagAttribute forUpdateTagAttribute = getAttribute("forUpdate");

    public SecureLoadTaskHandler(TagConfig config)
    {
        super(config);
    }

    protected JbpmActionListener getListener(FaceletContext ctx)
    {
        return new SecureLoadTasksActionListener(getValueExpression(idTagAttribute, ctx, Long.class), getValueExpression(targetTagAttribute, ctx, TaskInstance.class), getValueExpression(forUpdateTagAttribute, ctx, Boolean.class));
    }
}
