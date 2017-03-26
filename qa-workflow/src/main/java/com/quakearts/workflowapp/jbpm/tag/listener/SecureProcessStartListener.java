package com.quakearts.workflowapp.jbpm.tag.listener;

import java.util.Collection;
import java.util.Date;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.JbpmJsfContext;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.taskmgmt.exe.TaskMgmtInstance;

public class SecureProcessStartListener implements JbpmActionListener {
    private final ValueExpression processExpression;
    private final ValueExpression instanceExpression;
    private final ValueExpression taskInstanceExpression;

    public SecureProcessStartListener(ValueExpression processExpression, ValueExpression instanceExpression,ValueExpression taskInstanceExpression)
    {
        this.processExpression = processExpression;
        this.instanceExpression = instanceExpression;
        this.taskInstanceExpression = taskInstanceExpression;
    }

    public String getName()
    {
        return "startProcess";
    }

    @SuppressWarnings("rawtypes")
	public void handleAction(JbpmJsfContext context, ActionEvent event){
        javax.el.ELContext elContext;
        Object processValue;
        ProcessInstance instance;
        FacesContext facesContext;
        facesContext = FacesContext.getCurrentInstance();
        elContext = facesContext.getELContext();
        try{
            processValue = processExpression.getValue(elContext);
            if(processValue == null){
                context.setError("Error starting process", "The process value is null");
                return;
            }
        }
        catch(Exception ex){
            context.setError("Error starting process", ex);
            return;
        }
        if(!(processValue instanceof ProcessDefinition)){
            context.setError("Error starting process", "Attempted to start something other than a process");
            return;
        }
        ProcessDefinition definition = (ProcessDefinition)processValue;
        String actorId = context.getJbpmContext().getActorId();
        TaskInstance startTaskInstance=null;
        //Check for already started processes.        
        Collection taskList;
        Session session = context.getJbpmContext().getSession();
        Query query = session.createQuery("select ti from org.jbpm.taskmgmt.exe.TaskInstance as ti where ti.actorId = :actorId and ti.processInstance.processDefinition.id = :pdid and ti.end is null");
        query.setString("actorId",actorId);
        query.setLong("pdid",definition.getId());
        taskList = query.list();

        if(taskList.size()>0)
            for(Object obj:taskList){
                if(obj instanceof TaskInstance)
                    if(((TaskInstance)obj).isStartTaskInstance() && !((TaskInstance)obj).isSuspended()){
                        startTaskInstance = (TaskInstance)obj;
                        startTaskInstance.getProcessInstance().setStart(new Date());
                        startTaskInstance.setStart(new Date());
                        context.addSuccessMessage("Redirecting to already started workflow...");
                        break;
                    }
            }



        if(startTaskInstance==null){
            instance = definition.createProcessInstance();
            context.addSuccessMessage("Started process");
            TaskMgmtInstance taskMgmtInstance = instance.getTaskMgmtInstance();
            startTaskInstance = taskMgmtInstance.createStartTaskInstance();
            if(instance.hasEnded()){
                context.selectOutcome("finished");
                context.addSuccessMessage("Process completed");
            }
            if(instanceExpression != null){
                instanceExpression.setValue(elContext, instance);
            }
            context.getJbpmContext().save(instance);
        }
        context.selectOutcome((startTaskInstance==null)?"started-no-task":"started-task");
        
        if(taskInstanceExpression !=null){
            taskInstanceExpression.setValue(elContext,startTaskInstance);
        }
    }
}
