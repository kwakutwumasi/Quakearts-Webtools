package com.quakearts.workflowapp.jbpm.tag.handler;

import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.core.handler.AbstractHandler;

import com.quakearts.workflowapp.jbpm.tag.listener.HashGenerationListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class HashGenerationHandler extends AbstractHandler {

	TagAttribute valueAttribute = getRequiredAttribute("value"), 
	inputAttribute = getRequiredAttribute("input"),
	saltAttribute = getAttribute("salt"),
	algorithmAtribute = getAttribute("algorithm"),
	iterationsAtribute = getAttribute("iter");
	
	public HashGenerationHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected JbpmActionListener getListener(FaceletContext context) {
		return new HashGenerationListener(getValueExpression(valueAttribute, context, String.class),
				getValueExpression(inputAttribute, context, String.class),
				getValueExpression(saltAttribute, context, String.class),
				getValueExpression(algorithmAtribute, context, String.class),
				getValueExpression(iterationsAtribute, context, Object.class));
	}

}
