package com.quakearts.workflowapp.jbpm.tag.listener;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;


import org.jboss.logging.Logger;

import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.JbpmJsfContext;
import org.jbpm.taskmgmt.exe.TaskInstance;

public final class SecureLoadTasksActionListener
    implements JbpmActionListener
{

    private final ValueExpression idExpression;
    private final ValueExpression targetExpression;
    @SuppressWarnings("unused")
	private final ValueExpression forUpdateExpression;
    private static final Logger log = Logger.getLogger(SecureLoadTasksActionListener.class);

    public SecureLoadTasksActionListener(ValueExpression idExpression, ValueExpression targetExpression, ValueExpression forUpdateExpression)
    {
        this.idExpression = idExpression;
        this.targetExpression = targetExpression;
        this.forUpdateExpression = forUpdateExpression;
    }

    public String getName()
    {
        return "loadTask";
    }

    public void handleAction(JbpmJsfContext context, ActionEvent event)
    {
        javax.el.ELContext elContext;
        Object idValue;
        long id;
        boolean forUpdate;
        org.jbpm.taskmgmt.exe.TaskInstance taskInstance;
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            elContext = facesContext.getELContext();
            idValue = idExpression.getValue(elContext);
            if(idValue == null) {
                context.setError("Error loading task instance", "The ID value is null");
                return;
            }
        } catch(Exception ex) {
            context.setError("Error loading task", ex);
            return;
        }
        if(idValue instanceof Long) {
            id = ((Long)idValue).longValue();
        } else {
            id = Long.valueOf(idValue.toString()).longValue();
        }
        forUpdate = event.getPhaseId() != PhaseId.RENDER_RESPONSE;
        if(forUpdate) {
            taskInstance = context.getJbpmContext().getTaskInstanceForUpdate(id);
        } else {
            taskInstance = context.getJbpmContext().getTaskInstance(id);
        }
        if(taskInstance == null) {
            context.setError("Error loading task instance", (new StringBuffer()).append("No task instance was found with an ID of ").append(id).toString());
            targetExpression.setValue(elContext, new TaskInstance("null task","no actor"));
            context.selectOutcome("error");
            return;
        } else {
            String actorID = context.getJbpmContext().getActorId();
			if (actorID == null)
				actorID = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

            log.trace(new StringBuffer("context.getJbpmContext().getActorId() returns '").append(actorID).append("'"));
            String taskActor = taskInstance.getActorId();
            log.trace(new StringBuffer("taskInstance.getActorId() returns '").append(taskActor).append("'"));
            if(taskActor != null) {
                if(!taskActor.equals(actorID)) {
                    context.setError("Error loading task instance", (new StringBuffer()).append("Current user ").append(actorID).append(" has not been assigned this task.").toString());
                    targetExpression.setValue(elContext, new TaskInstance("null task","no actor"));
                    context.selectOutcome("error");
                    return;                    
                }
            }
        }
        targetExpression.setValue(elContext, taskInstance);
        context.selectOutcome("success");
    }
}
