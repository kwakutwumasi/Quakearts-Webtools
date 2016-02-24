package com.quakearts.workflowapp.jbpm.tag.listener;

import java.util.Collections;
import java.util.List;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.jbpm.db.TaskMgmtSession;
import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.JbpmJsfContext;
import org.jbpm.taskmgmt.exe.TaskInstance;
import com.quakearts.webapp.facelets.util.ObjectExtractor;

public class SecureListTasksActionListener implements JbpmActionListener {
	private final ValueExpression targetExpression;
	private final ValueExpression fnExpression;
	private final ValueExpression frExpression;
	private final ValueExpression fsExpression;
	private final ValueExpression feExpression;

	public SecureListTasksActionListener(ValueExpression targetExpression,
			ValueExpression fnExpression, ValueExpression frExpression,
			ValueExpression fsExpression, ValueExpression feExpression) {
		this.targetExpression = targetExpression;
		this.fnExpression = fnExpression;
		this.frExpression = frExpression;
		this.fsExpression = fsExpression;
		this.feExpression = feExpression;
	}

	public String getName() {
		return "listTasks";
	}

	@SuppressWarnings({ "unchecked" })
	public void handleAction(JbpmJsfContext context, ActionEvent event) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			javax.el.ELContext elContext = facesContext.getELContext();
			String actorId = context.getJbpmContext().getActorId();
			if (actorId == null)
				actorId = facesContext.getExternalContext().getRemoteUser();
			if (actorId == null) {
				context.setError("User cannot be retrieved");
				return;
			}
			TaskMgmtSession session = context.getJbpmContext().getTaskMgmtSession();
			
            boolean fn = ObjectExtractor.extractBoolean(fnExpression, elContext);
            boolean fe = ObjectExtractor.extractBoolean(feExpression, elContext);
            boolean fs = ObjectExtractor.extractBoolean(fsExpression, elContext);
            boolean fr = ObjectExtractor.extractBoolean(frExpression, elContext);
			
			List<TaskInstance> list=null;
			
			if(fn && fe && fr){
				list = context.getJbpmContext().getTaskList(actorId,fs);

				if (list != null)
					list.addAll(session.findPooledTaskInstances(actorId,fs));
				else
					list = session.findPooledTaskInstances(actorId,fs);

			} else {
				if(fn){
					list = session.findNotStartedTaskInstances(actorId, fs);
					list.addAll(session.findPooledTaskInstances(actorId,fs));
				}
				if(fe){
					if (list != null)
						list.addAll(session.findEndedTaskInstances(actorId,fs));
					else
						list = session.findEndedTaskInstances(actorId,fs);
				}
				if(fr){
					if (list != null)
						list.addAll(session.findRunningTaskInstances(actorId,fs));
					else
						list = session.findRunningTaskInstances(actorId,fs);
				}
			}
			
			targetExpression.setValue(elContext, list!=null?list:Collections.emptyList());
			context.selectOutcome("success");
		} catch (Exception ex) {
			context.setError("Error loading task list", ex);
			return;
		}
	}
}
