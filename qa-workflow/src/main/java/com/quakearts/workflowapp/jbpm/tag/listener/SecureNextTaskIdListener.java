package com.quakearts.workflowapp.jbpm.tag.listener;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.hibernate.Query;
import org.hibernate.Session;

import org.jboss.logging.Logger;

import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.JbpmJsfContext;

public class SecureNextTaskIdListener implements JbpmActionListener {
    private final ValueExpression idExpression;
    private final ValueExpression targetExpression ;
    private static Logger log = Logger.getLogger(SecureNextTaskIdListener.class);
    
    public SecureNextTaskIdListener(ValueExpression idExpression, ValueExpression targetExpression ) {
        this.idExpression = idExpression;
        this.targetExpression = targetExpression ;
    }

    public String getName() {
        return "nextTaskId";
    }

    public void handleAction(JbpmJsfContext context, ActionEvent event) {
        ELContext elContext;
        FacesContext facesContext;
        Object idValue;
        Long id;
        try{
            facesContext = FacesContext.getCurrentInstance();
            elContext = facesContext.getELContext();
            idValue = idExpression.getValue(elContext);
            if(idValue == null){
                context.setError("Error finding next task", "The id is null");
                return;
            }
        }
        catch(Exception ex){
            context.setError("Error finding next task", ex);
            return;
        }
        if(idValue instanceof Long){
            id = (Long) idValue;
        }else{
            try{
                id = Long.parseLong(idValue.toString());
            } catch (Exception e){
                context.setError("Error finding next task", e);
                return;                
            }
        }
        
        Session session = context.getJbpmContext().getSession();
        String actorId = context.getJbpmContext().getActorId();
        if(actorId == null)
            actorId = facesContext.getExternalContext().getRemoteUser();
        if(actorId == null){
            context.setError("User cannot be retrieved");
            return;
        }
        Long taskId=null, pooledtaskId=null;
        Query query = session.createQuery("select min(ti.id) from org.jbpm.taskmgmt.exe.TaskInstance as ti where ti.actorId = :actorId and ti.id > :id");
        query.setString("actorId",actorId);
        query.setLong("id",id);
        Object taskObj = query.uniqueResult();
        log.trace("Task query returned "+taskObj+" as the next task.");
        query = session.createQuery("select min(ti.id) from org.jbpm.taskmgmt.exe.PooledActor pooledActor join pooledActor.taskInstances ti where pooledActor.actorId = :swimlaneActorId and ti.actorId is null and ti.id > :id");
        query.setString("swimlaneActorId",actorId);
        query.setLong("id",id);
        Object pooledtaskObj = query.uniqueResult();
        log.trace("Pooled task query returned "+pooledtaskObj+" as the next task.");

        if(taskObj instanceof Long){
            taskId = (Long) taskObj;
        }
        if(pooledtaskObj instanceof Long){
            pooledtaskId = (Long) pooledtaskObj;
        }
        Long maxid = taskId==null || pooledtaskId ==null ?(pooledtaskId!=null?pooledtaskId:(taskId!=null?taskId:null)):(taskId.longValue()<pooledtaskId.longValue()?taskId:pooledtaskId);
        
        log.trace("Next id is "+maxid);
        targetExpression.setValue(elContext, maxid);
    }
}
