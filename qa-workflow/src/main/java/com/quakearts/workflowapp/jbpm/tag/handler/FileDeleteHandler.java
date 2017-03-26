package com.quakearts.workflowapp.jbpm.tag.handler;

import com.quakearts.workflowapp.jbpm.tag.listener.FileDeleteListener;
import javax.faces.view.facelets.FaceletContext;

import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.core.handler.AbstractHandler;
import org.jbpm.taskmgmt.exe.TaskInstance;

public class FileDeleteHandler extends AbstractHandler {

    private final TagAttribute taskTagAttribute = getRequiredAttribute("task");
    private final TagAttribute ticketTagAttribute=getRequiredAttribute("ticket");
    private final TagAttribute variableTagAttribute=getRequiredAttribute("variable");

    public FileDeleteHandler(TagConfig config) {
        super(config);
    }

    protected JbpmActionListener getListener(FaceletContext ctx) {
        return new FileDeleteListener(getValueExpression(taskTagAttribute, ctx, TaskInstance.class),getValueExpression(ticketTagAttribute,ctx,String.class),getValueExpression(variableTagAttribute, ctx, String.class));
    }
}
