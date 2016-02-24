package com.quakearts.workflowapp.jbpm.tag.listener;

import java.util.Collections;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.JbpmJsfContext;

import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class SecureListProcessInstancesListener implements JbpmActionListener {
    private final ValueExpression targetExpression;
    private final ValueExpression piKeyExpression;
    private final ValueExpression fnExpression;
    private final ValueExpression frExpression;
    private final ValueExpression fsExpression;
    private final ValueExpression feExpression;
    private final ValueExpression nameExpression;
    
	public SecureListProcessInstancesListener(ValueExpression targetExpression,
			ValueExpression piKeyExpression, ValueExpression fnExpression,
			ValueExpression frExpression, ValueExpression fsExpression,
			ValueExpression feExpression, ValueExpression nameExpression) {
		this.targetExpression = targetExpression;
		this.piKeyExpression = piKeyExpression;
		this.fnExpression = fnExpression;
		this.frExpression = frExpression;
		this.fsExpression = fsExpression;
		this.feExpression = feExpression;
		this.nameExpression = nameExpression;
	}

    public String getName() {
        return "listProcesses";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void handleAction(JbpmJsfContext context, ActionEvent actionEvent) {
        try
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            javax.el.ELContext elContext = facesContext.getELContext();
            Session session = context.getJbpmContext().getSession();
            String actorId = context.getJbpmContext().getActorId();
            if(actorId == null)
                actorId = facesContext.getExternalContext().getRemoteUser();
            if(actorId == null){
                context.setError("User cannot be retrieved");
                return;
            }
            List processList;
            String piKey = ObjectExtractor.extractString(piKeyExpression, elContext);
            String name = ObjectExtractor.extractString(nameExpression, elContext);
            boolean fn = ObjectExtractor.extractBoolean(fnExpression, elContext);
            boolean fe = ObjectExtractor.extractBoolean(feExpression, elContext);
            boolean fs = ObjectExtractor.extractBoolean(fsExpression, elContext);
            boolean fr = ObjectExtractor.extractBoolean(frExpression, elContext);
            
            StringBuilder filterBuilder = new StringBuilder();
            if(fn&&fe&&fr){
            	processList = context.getJbpmContext().getGraphSession().findProcessInstancesForActor(actorId, fs);
            } else {	
	            if(!fn){
	            	filterBuilder.append(" and pi.start != null");
	            }
	
	            if(!fe){
	            	filterBuilder.append(" and pi.end == null");
	            }
	
	            if(!fr){
	            	filterBuilder.append(" and pi.end != null");
	            }
	            
            	filterBuilder.append(" and (pi.suspended = false or pi.suspended ="+(fn)+")");
	     
	            Query query = session.createQuery("select distinct pi from org.jbpm.taskmgmt.exe.TaskInstance as ti join ti.processInstance as pi "
	            		+ "where ti.actorId = :actorId"
	            		+(isNotEmpty(name)?" and pi.name = :name ":"")
	            		+(isNotEmpty(piKey)?" and pi.key = :key ":"")+filterBuilder.toString());
	            query.setString("actorId",actorId);
	            processList = query.list();
            }
            targetExpression.setValue(elContext, Collections.unmodifiableList(processList));
            context.selectOutcome("success");
        }
        catch(Exception ex)
        {
            context.setError("Error loading process list", ex);
            return;
        }
    }
    
    private boolean isNotEmpty(String value){
    	return (value!=null && value.trim().length()!=0);
    }
}
