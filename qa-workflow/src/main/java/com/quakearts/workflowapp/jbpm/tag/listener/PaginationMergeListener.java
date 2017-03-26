package com.quakearts.workflowapp.jbpm.tag.listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

import org.hibernate.LazyInitializationException;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.TaskInstance;

import com.quakearts.webapp.facelets.bootstrap.listeners.PaginationActionListener;
import com.quakearts.webapp.facelets.bootstrap.listeners.PaginationEvent;
import com.quakearts.workflowapp.jbpm.tag.listener.beans.ProcessInstanceReport;
import com.quakearts.workflowapp.jbpm.tag.listener.beans.TaskInstanceReport;
import com.quakearts.workflowapp.jbpm.util.QuakeArtsjBPMFunctions;

public class PaginationMergeListener implements PaginationActionListener {

	final private ValueExpression metadataValueExpression;
	
	public PaginationMergeListener(ValueExpression metadataValueExpression) {
		this.metadataValueExpression = metadataValueExpression;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void handleEvent(PaginationEvent event) {
		JbpmContext ctx = JbpmConfiguration.getInstance().createJbpmContext();
		try{
			if(event.getValue() instanceof List<?>){
				
				Map<Object, Object> metadata = (Map<Object, Object>) metadataValueExpression.getValue(FacesContext.getCurrentInstance().getELContext());
				
				if(metadata==null){
					metadata = new HashMap<Object, Object>();
					metadataValueExpression.setValue(FacesContext.getCurrentInstance().getELContext(), metadata);
				}
				
				List list =  (List) event.getValue();
				if(list.size()>0)
				for(int i= event.getStart(); i<list.size() && i<event.getEnd();i++){
					Object object = list.get(i);
					if(metadata.containsKey(object))
						continue;
					
					if(object instanceof TaskInstance){
						TaskInstance ti = ((TaskInstance)object), oldTi = ti;
						try{
							ti.getPooledActors().size();
							ti.getProcessInstance().getProcessDefinition().getName();
						} catch (LazyInitializationException e){
							ti = (TaskInstance) ctx.getSession().merge(ti);
						}
						
						TaskInstanceReport taskReport = new TaskInstanceReport();
						
						taskReport.setProcessName(ti.getProcessInstance().getProcessDefinition().getName());
						if(ti.getActorId()!=null && !ti.getActorId().trim().isEmpty())
							taskReport.setActorFullName(QuakeArtsjBPMFunctions.getFullName(ti.getActorId()));
						
						TaskInstance initalTask = QuakeArtsjBPMFunctions.getInitialTask(ti.getProcessInstance());
						if(initalTask!=null){
							taskReport.setInitiationDate(initalTask.getStart());
							taskReport.setInitiator(QuakeArtsjBPMFunctions.getFullName(initalTask.getActorId()));
						}
						if(ti.getPooledActors().size()>0){
							taskReport.setPooledActors(new ArrayList(ti.getPooledActors()));
							Collections.sort(taskReport.getPooledActors(),new Comparator<PooledActor>() {
								@Override
								public int compare(PooledActor o1, PooledActor o2) {
									return o1.getActorId().compareTo(o2.getActorId());
								}
							});
						}
						metadata.put(oldTi, taskReport);
					} else if(object instanceof ProcessInstance){
						ProcessInstance pi = (ProcessInstance) object, oldPi=pi;
						ProcessInstanceReport piReport = new ProcessInstanceReport();
						String actor = QuakeArtsjBPMFunctions.getInitiator(pi);
						if(actor!=null && !actor.trim().isEmpty())
							piReport.setInitiatedBy(QuakeArtsjBPMFunctions.getFullName(actor));
						
						String processName;
						try{
							processName = pi.getProcessDefinition().getName();
						} catch (LazyInitializationException e){
							processName = ((ProcessInstance) ctx.getSession().merge(pi)).getProcessDefinition().getName();
						}
						piReport.setProcessName(processName);
						
						piReport.setInitiatedByUsername(actor);
						metadata.put(oldPi, piReport);
					}
				}
			}
		} finally {
			ctx.close();
		}
	}
}
