package com.quakearts.workflowapp.jbpm.tag.handler;

import com.quakearts.workflowapp.jbpm.tag.listener.SecureListProcessInstancesListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.core.handler.AbstractHandler;

import java.util.List;

public class SecureListProcessInstancesHandler extends AbstractHandler {
    private final TagAttribute targetAttribute = getRequiredAttribute("target"),
    		nameAttribute = getAttribute("name"), piKeyAttribute = getAttribute("piKey"),
    		fnAttribute = getAttribute("fn"), feAttribute = getAttribute("fe"), 
    		fsAttribute = getAttribute("fs"), frAttribute = getAttribute("fr");

    public SecureListProcessInstancesHandler(TagConfig config) {
        super(config);
    }

    protected JbpmActionListener getListener(FaceletContext context) {
        return new SecureListProcessInstancesListener(getValueExpression(targetAttribute,context,List.class),
        		getValueExpression(piKeyAttribute, context, String.class),
        		getValueExpression(fnAttribute, context, Boolean.class),
        		getValueExpression(frAttribute, context, Boolean.class),
        		getValueExpression(fsAttribute, context, Boolean.class),
        		getValueExpression(feAttribute, context, Boolean.class),
        		getValueExpression(nameAttribute, context, String.class));
    }
}
