package com.quakearts.workflowapp.jbpm.tag.handler;

import java.util.Collection;

import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.core.handler.AbstractHandler;

import com.quakearts.workflowapp.jbpm.tag.listener.IncompleteProcessTasksListener;
import javax.faces.view.facelets.FaceletContext;
import javax.faces.view.facelets.TagAttribute;
import javax.faces.view.facelets.TagConfig;

public class IncompleteProcessTasksHandler extends AbstractHandler {
	private final TagAttribute processInstanceTagAtrribute = getRequiredAttribute("process"),
	tasksTagAttribute = getRequiredAttribute("tasks");
	
	
	public IncompleteProcessTasksHandler(TagConfig config) {
		super(config);
	}

	@Override
	protected JbpmActionListener getListener(FaceletContext ctx) {
		return new IncompleteProcessTasksListener(
				getValueExpression(processInstanceTagAtrribute,ctx,ProcessInstance.class),
				getValueExpression(tasksTagAttribute,ctx,Collection.class));
	}

}
