package com.quakearts.workflowapp.jbpm.util.resolvers;

import com.quakearts.workflowapp.jbpm.data.JbpmTaskDatasource;
import com.quakearts.workflowapp.jbpm.util.filter.Filter;
import com.quakearts.workflowapp.jbpm.xml.assignment.Assignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.jboss.logging.Logger;
import org.jbpm.JbpmConfiguration;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.taskmgmt.def.AssignmentHandler;
import org.jbpm.taskmgmt.exe.Assignable;
import org.jbpm.taskmgmt.exe.TaskInstance;

import static com.quakearts.workflowapp.jbpm.util.QuakeArtsjBPMFunctions.*;


public class DynamicActorResolver implements AssignmentHandler {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3473275313901868742L;
	private String file=null, roleName=null, actor=null;
	boolean assignToSelf = false;
    private static final Logger log = Logger.getLogger(DynamicActorResolver.class);
    private ArrayList<String> actors = null;
    private HashMap<String,String> variableMap = null;
    private ExecutionContext executionContext = null;
    private Assignment.Role roleNode = null;
    private Assignment assign = null;
	private JbpmTaskDatasource datasource;
    
    public DynamicActorResolver() {
    }

    @SuppressWarnings("unchecked")
	public void assign(Assignable assignable, ExecutionContext executionContext) throws Exception {
        actors = new ArrayList<String>();
        variableMap = new HashMap<String,String>();
        
        if(file==null)
            throw new ResolverException("Could not obtain file to search for actors. File variable is null.");

        if(roleName==null)
            throw new ResolverException("Could not obtain role name to search for actors. Role name variable is null.");
            
        if(log.isTraceEnabled()){
            TaskInstance task=null;
            task = (assignable instanceof TaskInstance)?((TaskInstance)assignable):null;
            log.trace("Assigning "+((task!=null)?" task "+task.getName()+" with id "+task.getId()+"...":"..."));
        }
        this.executionContext = executionContext;

        parseAssignment();
        getRoleInfo();
        resolveUserVars();
        resolveProcessVars();

        if(roleNode == null)
            throw new ResolverException("Could not obtain role "+roleName+" to search for actors.");

        List<Assignment.Role.Query> queries = roleNode.getQuery();
        if(queries.size()>0){    
            Filter filter = new Filter(variableMap);
            log.trace("Got "+queries.size()+" queries.");
            Assignment.Role.Query query=null;
            for(int i=0;i<queries.size();i++){
                query = queries.get(i);
                boolean execute = true;
                if(query.getCondition()!=null){
                    log.trace("Evaluating condition "+query.getCondition());
                    execute=filter.evaluate(query.getCondition());
                    log.trace("Condition is "+execute);                    
                }
                if(execute)
                    if(query.getName()!=null){
                        datasource.setQueryName(query.getName());
                        datasource.setDetail("VARS", variableMap);
                        if(datasource.search()){
                        	actors = (ArrayList<String>) datasource.getDetail("TASKACTORS");
                        }
                    }else{
                        log.error("Query name was null");
                    }
            }
            if(actors!=null&&actors.size()>0){
                switch(actors.size()){
                    case 1:
                        log.trace("Assigning task to actor "+actors.get(0));
                        assignable.setActorId(actors.get(0).trim());
                        return;
                    default:
                        String[] actors_arr = new String[actors.size()];
                        actors_arr = actors.toArray(actors_arr);
                        if(log.isTraceEnabled()){
                            for(String pooledActor:actors){
                                log.trace("Assigning actor "+pooledActor+" to actor pool.");
                            }
                        }
                        assignable.setPooledActors(actors_arr);
                        return;
                }
            }else{
                throw new ResolverException("Cannot assign task. No actor found.");
            }
        }else{
            if(roleNode.getActor().size()>0){
                switch(roleNode.getActor().size()){
                    case 1:
                        log.trace("Assigning task to actor "+roleNode.getActor().get(0).getUsername());
                        if(assignToSelf || !roleNode.getActor().get(0).getUsername().equals(actor)){
                        	assignable.setActorId(roleNode.getActor().get(0).getUsername().trim());
                        }
                        return;
                    default:
                        List<String> actors_arr = new ArrayList<String>();
                        for(int i=0;i<roleNode.getActor().size();i++){
                        	String toassign = roleNode.getActor().get(i).getUsername().trim();
                            if(assignToSelf || !toassign.equals(actor)){
                            	log.trace("Assigning actor "+toassign+" to actor pool.");
                            	actors_arr.add(toassign);
                            }
                        }
                        assignable.setPooledActors(actors_arr.toArray(new String[actors_arr.size()]));
                        return;
                }
            }else{
                throw new ResolverException("No query or actor to get actors to assign task.");
            }
        }
    }

    private void parseAssignment() throws JAXBException, FileNotFoundException, ResolverException, IOException {
    	
    	assign = (Assignment) retrieve(file, Assignment.class);
    	if(assign!=null)
    		return;
    	
        JAXBContext parserContext = null;
        Unmarshaller unmarshaller = null;

        parserContext = JAXBContext.newInstance("com.quakearts.workflowapp.jbpm.xml.assignment",this.getClass().getClassLoader());
        unmarshaller = parserContext.createUnmarshaller();
        String assignmentfilelocation; 
        InputStream fis = null;
        try {
	        try {
	            assignmentfilelocation = JbpmConfiguration.Configs.getString("com.quakearts.assignment.xml.base.location");	            
	            fis = new FileInputStream(assignmentfilelocation+File.separator+file);
			} catch (FileNotFoundException e) {
				fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
				if(fis==null)
					throw e;
			}
	        
	        Object obj = unmarshaller.unmarshal(fis);
	        if(obj instanceof Assignment){
	            log.trace("Found "+file+" as valid Assignement XML file.");
	            assign = (Assignment) obj;
	            if(log.isTraceEnabled()){
	            	Marshaller marshaller = parserContext.createMarshaller();
	            	ByteArrayOutputStream bis;
	            	marshaller.marshal(obj, (bis = new ByteArrayOutputStream()));
	            	log.trace(new String(bis.toByteArray()));
	            }
	            store(file, assign);
	        }else{
	            throw new ResolverException("No assignment file to search for actors.");            
	        }
        } finally {
        	try {
            	fis.close();
			} catch (Exception e) {
			}
        }
    }
    
    private void getRoleInfo() throws ResolverException {
        if(assign!=null){
            for(Assignment.Role role:assign.getRole()){
                if(role.getName().trim().equalsIgnoreCase(roleName.trim())){
                    roleNode=role;
                    break;
                }
            }
        }else{
            throw new ResolverException("No assignment file to obtain role.");
        }
    }

    
	private void resolveUserVars() throws NamingException{
        log.trace("Populating user roles vars....");
        if(actor==null){
        	TaskInstance ti = getPreviousTask(executionContext.getProcessInstance());
        	if(ti!=null)
        		actor = ti.getActorId();
        	else
        		return;
        }
        log.trace("Actor for previous task was "+actor);
        
        variableMap.put("actor",actor);
        datasource = getDatasource();
        datasource.setQueryName("ACTORVARS");
        datasource.setDetail("ACTOR", actor);
        if(datasource.search()){
        	@SuppressWarnings("unchecked")
			Map<String, String> actorVariables = (Map<String, String>) datasource.getDetail("VARS");
        	if(actorVariables!=null)
        		variableMap.putAll(actorVariables);
        }
    }

    @SuppressWarnings("rawtypes")
	private void resolveProcessVars(){
        log.trace("Populating process vars....");
        Map map = executionContext.getContextInstance().getVariables();
        if(map!=null){
            Set keys = map.keySet();
            if(keys!=null)
                for(Object key:keys){
                    Object value = executionContext.getVariable(key.toString());
                    if(value!=null){
                        variableMap.put(String.valueOf(key),value.toString());
                        log.trace("variableMap.put('"+key+"','"+value.toString()+"')");
                    }
                }
        }
    }

    public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isAssignToSelf() {
		return assignToSelf;
	}

	public void setAssignToSelf(boolean assignToSelf) {
		this.assignToSelf = assignToSelf;
	}

}
