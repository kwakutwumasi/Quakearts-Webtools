package com.quakearts.workflowapp.jbpm.util.resolvers;

import java.util.List;
import java.util.StringTokenizer;
import org.jboss.logging.Logger;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;
import org.jbpm.taskmgmt.exe.TaskInstance;

public class PooledActorResolver implements AssignmentHandler {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6484711142409509787L;
	String actors;
    private static final Logger log = Logger.getLogger("PooledActorResolver");
    public PooledActorResolver() {
    }

    @SuppressWarnings("rawtypes")
	public void assign(Assignable assignable, ExecutionContext executionContext) {
        TaskInstance task=null;
        if(log.isTraceEnabled()){
            task = (assignable instanceof TaskInstance)?((TaskInstance)assignable):null;
            log.trace("Assigning "+((task!=null)?"task "+task.getName()+" with id "+task.getId()+"...":"..."));
        }
        String assignee = null;
        int mintasksize = Integer.MAX_VALUE,tasksize=0;
        JbpmContext context = executionContext.getJbpmContext();
        List tasks=null;
        StringTokenizer tokenizer = new StringTokenizer(actors,",");
        String actor;
        while(tokenizer.hasMoreTokens()){
            actor = tokenizer.nextToken();
            tasks = context.getTaskList(actor,true);
            tasksize=(tasks!=null)?tasks.size():0;
            if(mintasksize>(tasksize)){
                if(log.isTraceEnabled())
                    log.trace(assignee==null?"Assigning task "+((task!=null)?task.getName()+" ID: "+task.getId():" task ")+" to "+actor:"Current Assignee "+assignee+" has more tasks than "+actor+". Task"+((task!=null)?task.getName()+" ID: "+task.getId():" task ")+" will be reassigned to "+actor+".");
                assignee = actor;
                mintasksize = tasksize;
            }
        }
        assignable.setActorId(assignee);
    }
}
