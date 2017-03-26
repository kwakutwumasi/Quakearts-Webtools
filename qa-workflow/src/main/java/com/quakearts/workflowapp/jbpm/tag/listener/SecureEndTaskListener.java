package com.quakearts.workflowapp.jbpm.tag.listener;

import java.util.Date;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.JbpmJsfContext;
import org.jbpm.taskmgmt.exe.TaskInstance;

public class SecureEndTaskListener implements JbpmActionListener {
    private final ValueExpression taskExpression;

    public SecureEndTaskListener(ValueExpression taskExpression) {
        this.taskExpression = taskExpression;
    }

    public String getName() {
        return "endTask";
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
            if(task.getStart()==null)
                task.setStart(new Date());
            if(task.getEnd()==null)
                task.setEnd(new Date());
            context.addSuccessMessage("Task "+task.getId()+" has been successfully ended.");
            context.getJbpmContext().getSession().flush();
            context.selectOutcome("success");
        }else{
            context.setError("Cannot reset. ");
            context.selectOutcome("error");
            return;
        } 
        
    }
}
