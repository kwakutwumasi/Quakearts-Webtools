package com.quakearts.workflowapp.jbpm.tag.handler;

import com.quakearts.workflowapp.jbpm.tag.listener.UpdateProcessFileListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.core.handler.AbstractHandler;

public class UpdateProcessFileHandler extends AbstractHandler {
    private final TagAttribute inStreamTagAttribute = getAttribute("data");
    private final TagAttribute idTagAttribute = getRequiredAttribute("id");
    private final TagAttribute fileNameTagAttribute = getAttribute("filename");

    public UpdateProcessFileHandler(TagConfig config) {
        super(config);
    }

    protected JbpmActionListener getListener(FaceletContext ctx) {
        return new UpdateProcessFileListener(getValueExpression(inStreamTagAttribute,ctx,Object.class),getValueExpression(idTagAttribute,ctx,Long.class),getValueExpression(fileNameTagAttribute,ctx,String.class));
    }
}
