package com.quakearts.workflowapp.jbpm.tag.handler;

import com.quakearts.workflowapp.jbpm.tag.listener.SecureListTasksActionListener;

import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;



import java.util.List;

import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.core.handler.AbstractHandler;

public class SecureListTasksHandler extends AbstractHandler {
    
    private final TagAttribute targetAttribute = getRequiredAttribute("target"),
		fnAttribute = getAttribute("fn"), feAttribute = getAttribute("fe"), 
		fsAttribute = getAttribute("fs"), frAttribute = getAttribute("fr");

    public SecureListTasksHandler(TagConfig config) {
        super(config);
    }

    protected JbpmActionListener getListener(FaceletContext context) {
        return new SecureListTasksActionListener(getValueExpression(targetAttribute,context,List.class),
        		getValueExpression(fnAttribute, context, Boolean.class),
        		getValueExpression(frAttribute, context, Boolean.class),
        		getValueExpression(fsAttribute, context, Boolean.class),
        		getValueExpression(feAttribute, context, Boolean.class));
    }
}
