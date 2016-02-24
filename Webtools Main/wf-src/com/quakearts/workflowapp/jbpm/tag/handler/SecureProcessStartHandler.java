package com.quakearts.workflowapp.jbpm.tag.handler;

import com.quakearts.workflowapp.jbpm.tag.listener.SecureProcessStartListener;
import javax.faces.view.facelets.FaceletContext;

import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;


import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.core.handler.AbstractHandler;
import org.jbpm.taskmgmt.exe.TaskInstance;

public class SecureProcessStartHandler extends AbstractHandler {
    private final TagAttribute processTagAttribute = getRequiredAttribute("process");
    private final TagAttribute instanceTagAttribute = getRequiredAttribute("target");
    private final TagAttribute taskInstanceTagAttribute = getRequiredAttribute("task");

    public SecureProcessStartHandler(TagConfig config) {
        super(config);
    }

    protected JbpmActionListener getListener(FaceletContext ctx) {
        return new SecureProcessStartListener(getValueExpression(processTagAttribute, ctx, ProcessDefinition.class), getValueExpression(instanceTagAttribute, ctx, ProcessInstance.class),getValueExpression(taskInstanceTagAttribute, ctx, TaskInstance.class));
    }
}
