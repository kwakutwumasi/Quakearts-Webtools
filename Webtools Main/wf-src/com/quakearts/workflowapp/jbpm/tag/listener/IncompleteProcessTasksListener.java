package com.quakearts.workflowapp.jbpm.tag.listener;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.JbpmJsfContext;

public class IncompleteProcessTasksListener implements JbpmActionListener {
	final private ValueExpression tasksExpression, processInstanceExpression;

	public IncompleteProcessTasksListener(ValueExpression processInstanceExpression,
			ValueExpression taskIdExpression) {
		this.tasksExpression = taskIdExpression;
		this.processInstanceExpression = processInstanceExpression;
	}

	@Override
	public String getName() {
		return "incompleteProcessTasks";
	}

	@Override
	public void handleAction(JbpmJsfContext context, ActionEvent event) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			javax.el.ELContext elContext = facesContext.getELContext();
			final Object value = processInstanceExpression.getValue(elContext);
			if (value instanceof ProcessInstance) {
				final ProcessInstance processInstance = (ProcessInstance) value;
				tasksExpression.setValue(elContext, processInstance.getTaskMgmtInstance().getUnfinishedTasks(processInstance.getRootToken()));
				return;
			}else{
				context.setError("Error getting next task", "The value is not of type ProcessInstance");
				return;
			}
		} catch (Exception ex) {
			context.setError("Error getting next Task", ex);
			return;
		}		
	}

}
