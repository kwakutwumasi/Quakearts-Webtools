package com.quakearts.workflowapp.jbpm.tag.listener;

import java.util.List;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.JbpmJsfContext;

public final class SecureLoadProcessInstanceActionListener
    implements JbpmActionListener
{

    private final ValueExpression idExpression;
    private final ValueExpression targetExpression;
    @SuppressWarnings("unused")
	private final ValueExpression forUpdateExpression;

    public SecureLoadProcessInstanceActionListener(ValueExpression idExpression, ValueExpression targetExpression, ValueExpression forUpdateExpression)
    {
        this.idExpression = idExpression;
        this.targetExpression = targetExpression;
        this.forUpdateExpression = forUpdateExpression;
    }

    public String getName()
    {
        return "loadProcessInstance";
    }

    @SuppressWarnings("rawtypes")
	public void handleAction(JbpmJsfContext context, ActionEvent event)
    {
        javax.el.ELContext elContext;
        Object idValue;
        long id;
        boolean forUpdate;
        final FacesContext facesContext;
        org.jbpm.graph.exe.ProcessInstance processInstance;
        try
        {
            facesContext = FacesContext.getCurrentInstance();
            elContext = facesContext.getELContext();
            idValue = idExpression.getValue(elContext);
            if(idValue == null)
            {
                context.setError("Error loading process instance", "The ID value is null");
                return;
            }
        }
        catch(Exception ex)
        {
            context.setError("Error loading process instance", ex);
            return;
        }
        if(idValue instanceof Long)
        {
            id = ((Long)idValue).longValue();
        } else
        {
            id = Long.valueOf(idValue.toString()).longValue();
        }
        forUpdate = event.getPhaseId() != PhaseId.RENDER_RESPONSE;
        String actorId = context.getJbpmContext().getActorId();
        if(actorId==null)
        	actorId = facesContext.getExternalContext().getRemoteUser();
        	
        Session ses = context.getJbpmContext().getSession();
        SQLQuery query = ses.createSQLQuery("SELECT DISTINCT JBPM_PROCESSINSTANCE.ID_ FROM JBPM_PROCESSINSTANCE LEFT OUTER JOIN JBPM_TASKINSTANCE ON JBPM_PROCESSINSTANCE.ID_ = JBPM_TASKINSTANCE.PROCINST_ LEFT OUTER JOIN JBPM_TASKACTORPOOL LEFT OUTER JOIN JBPM_POOLEDACTOR ON JBPM_TASKACTORPOOL.POOLEDACTOR_ = JBPM_POOLEDACTOR.ID_ ON  JBPM_TASKINSTANCE.ID_ = JBPM_TASKACTORPOOL.TASKINSTANCE_ WHERE  (JBPM_TASKINSTANCE.ACTORID_ = :actor OR JBPM_POOLEDACTOR.ACTORID_ = :actor) AND JBPM_PROCESSINSTANCE.ID_ = :id");
        query.setString("actor",actorId);
        query.setLong("id",id);
        List rs = query.list();
        if(rs.size()<=0){
            context.setError("Error loading process instance", "Current user does not have access to any task in this process.");
            return;            
        }

        if(forUpdate)
        {
            processInstance = context.getJbpmContext().getProcessInstanceForUpdate(id);
        } else
        {
            processInstance = context.getJbpmContext().getProcessInstance(id);
        }
        if(processInstance == null)
        {
            context.setError("Error loading process instance", (new StringBuffer()).append("No process instance was found with an ID of ").append(id).toString());
            return;
        }
        targetExpression.setValue(elContext, processInstance);
        context.selectOutcome("success");
    }
}
