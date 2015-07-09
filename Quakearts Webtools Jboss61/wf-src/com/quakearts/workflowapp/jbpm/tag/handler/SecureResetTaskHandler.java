package com.quakearts.workflowapp.jbpm.tag.handler;

import com.quakearts.workflowapp.jbpm.tag.listener.SecureResetTaskListener;
import javax.faces.view.facelets.FaceletContext;

import javax.faces.view.facelets.TagAttribute;

import javax.faces.view.facelets.TagConfig;


import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.core.handler.AbstractHandler;
import org.jbpm.taskmgmt.exe.TaskInstance;

public class SecureResetTaskHandler extends AbstractHandler {
    private final TagAttribute taskTagAttribute = getRequiredAttribute("task");

    public SecureResetTaskHandler(TagConfig config) {
        super(config);
    }

    protected JbpmActionListener getListener(FaceletContext faceletContext) {
        return new SecureResetTaskListener(getValueExpression(taskTagAttribute,faceletContext,TaskInstance.class));
    }
}
