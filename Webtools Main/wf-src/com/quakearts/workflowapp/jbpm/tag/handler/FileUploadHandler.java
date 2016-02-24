package com.quakearts.workflowapp.jbpm.tag.handler;

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;
import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.core.handler.AbstractHandler;
import org.jbpm.taskmgmt.exe.TaskInstance;
import com.quakearts.workflowapp.jbpm.tag.listener.FileUploadListener;

public class FileUploadHandler extends AbstractHandler {

    private final TagAttribute taskTagAttribute = getRequiredAttribute("task");
    private final TagAttribute streamTagAttribute=getRequiredAttribute("stream");
    private final TagAttribute descTagAttribute=getRequiredAttribute("description");
    private final TagAttribute variableTagAttribute=getRequiredAttribute("variable");
    private final TagAttribute filenameTagAttribute=getRequiredAttribute("filename");

    public FileUploadHandler(TagConfig config) {
        super(config);
    }

    protected JbpmActionListener getListener(FaceletContext ctx) {
        return new FileUploadListener(getValueExpression(streamTagAttribute,ctx,byte[].class),
        		getValueExpression(taskTagAttribute, ctx, TaskInstance.class),
        		getValueExpression(descTagAttribute,ctx,String.class),
        		getValueExpression(variableTagAttribute, ctx, String.class),
        		getValueExpression(filenameTagAttribute, ctx, String.class));
    }
}
