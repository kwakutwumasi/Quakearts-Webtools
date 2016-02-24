package com.quakearts.workflowapp.jbpm.tag.listener;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.JbpmJsfContext;
import org.jbpm.taskmgmt.exe.TaskInstance;

public class SecureResetTaskListener implements JbpmActionListener {
    private final ValueExpression taskExpression;

    public SecureResetTaskListener(ValueExpression taskExpression) {
        this.taskExpression = taskExpression;
    }

    public String getName() {
        return "resetTask";
    }

    public void handleAction(JbpmJsfContext context, ActionEvent event) 
    {
        ELContext elcontext;
        FacesContext facescontext;
        TaskInstance task;
        Object taskValue;
        try{
            facescontext = FacesContext.getCurrentInstance();
            elcontext = facescontext.getELContext();
            
            taskValue = taskExpression.getValue(elcontext);
            if(taskValue == null){
                context.setError("Cannot reset. Task is null.");
                return;
            }
        }catch(Exception ex){
            context.setError("Cannot reset. Task",ex);
            context.selectOutcome("error");
            return;
        }
        if(taskValue instanceof TaskInstance){
            task = (TaskInstance) taskValue;
            if(task.getStart()!=null)
                task.setStart(null);
            if(task.getEnd()!=null)
                task.setEnd(null);
            context.addSuccessMessage("Task "+task.getId()+" has been successfully reset.");
            context.getJbpmContext().getSession().flush();
            context.selectOutcome("success");
        }else{
            context.setError("Cannot reset. ");
            context.selectOutcome("error");
            return;
        } 
        
    }
}
