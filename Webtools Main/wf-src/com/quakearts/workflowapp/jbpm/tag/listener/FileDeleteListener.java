package com.quakearts.workflowapp.jbpm.tag.listener;

import com.quakearts.workflowapp.jbpm.util.QuakeArtsjBPMFunctions;
import com.quakearts.workflowapp.jbpm.util.process.ProcessFile;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.jboss.logging.Logger;
import org.jbpm.JbpmConfiguration;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.JbpmJsfContext;
import org.jbpm.taskmgmt.exe.TaskInstance;


public class FileDeleteListener implements JbpmActionListener {
    
    private final ValueExpression taskExpression,variableExpression,ticketExpression;
    private static final Logger log = Logger.getLogger(FileDeleteListener.class);

    public FileDeleteListener(ValueExpression taskExpression,ValueExpression ticketExpression, ValueExpression variableExpression){
        this.taskExpression=taskExpression;
        this.variableExpression=variableExpression;
        this.ticketExpression=ticketExpression;        
    }

    public String getName() {
        return "deleteFile";
    }

    @SuppressWarnings("rawtypes")
	public void handleAction(JbpmJsfContext context, ActionEvent event) 
    {
        ELContext elcontext;
        FacesContext facescontext;
        TaskInstance task;
        String saveFileName="nosaveFileName",variable,ticket;
        Object taskValue,variableValue,ticketValue;
        log.trace("PhaseId = "+event.getPhaseId());
        try{
            facescontext = FacesContext.getCurrentInstance();
            elcontext = facescontext.getELContext();
            
            taskValue = taskExpression.getValue(elcontext);
            log.trace("taskExpression is '"+taskValue+"'");
            if(taskValue == null){
                context.setError("Cannot delete file. Task was null");
                context.selectOutcome("error");
                return;
            }
            variableValue = variableExpression.getValue(elcontext);
            log.trace("variableExpression is '"+variableValue+"'");
            if(variableValue == null){
                context.setError("Cannot delete file. Variable was null");
                context.selectOutcome("error");
                return;
            }
            ticketValue = ticketExpression.getValue(elcontext);
            log.trace("ticketExpression is '"+ticketValue+"'");
            if(ticketValue == null){
                context.setError("Cannot delete file. Ticket was null");
                context.selectOutcome("error");
                return;
            }
        }catch(Exception ex){
            context.setError("Cannot upload file",ex);
            context.selectOutcome("error");
            return;
        }

        if(taskValue instanceof TaskInstance){
            task = (TaskInstance)taskValue;
        }else{
            context.setError("Cannot delete file. Task variable is not an instance of TaskInstance");
            context.selectOutcome("error");
            return;
        }
        variable = variableValue.toString().trim();
        ticket = ticketValue.toString();
        if(!task.getActorId().equals(context.getJbpmContext().getActorId())){
            context.setError("Cannot delete file. User does not have access to task.");
            context.selectOutcome("error");
            return;                                    
        }
        if(task.getEnd()!=null){
            context.setError("Cannot delete file. Task has ended.");
            context.selectOutcome("error");
            return;                                                    
        }
        
        String tempfilelocation = JbpmConfiguration.Configs.getString("com.quakearts.file.temp.location");
        log.trace("tempfilelocation ='"+tempfilelocation+"'");
        String storelocation = tempfilelocation+"\\jpbm_task_files";
        File loc;
        loc = new File(storelocation);
        if(!loc.exists()){
            context.setError("Cannot delete file. Process file location does not exist .");
            context.selectOutcome("error");
            return;                                                    
        }

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            digest = null;
        }
        ProcessInstance pi;
        pi = task.getProcessInstance();
        saveFileName = pi.getProcessDefinition().getName()+" "+pi.getProcessDefinition().getVersion()+" "+task.getProcessInstance().getId();
        if(digest!=null){
            digest.update(saveFileName.getBytes());
            byte[] hash = digest.digest();
            saveFileName = QuakeArtsjBPMFunctions.toHexString(hash);
        }
        log.trace("saveFileName ='"+saveFileName+"'");
        
        Object collObj = task.getVariable(variable);
        Collection fileList;
        if(collObj instanceof Collection){
            fileList = (Collection) collObj;
        }else{
            context.setError("Cannot delete file. ProcessFile variable '"+variable+"' does not exist.");
            context.selectOutcome("error");
            return;                                                    
        }
        String filename=null;
        for(Object obj:fileList){
            if(obj instanceof ProcessFile){
                if(ticket.equals(((ProcessFile)obj).getTicket())){
                    fileList.remove((ProcessFile)obj);
                    filename = ((ProcessFile)obj).getFilename();
                    break;
                }
            }
        }
        task.setVariable(variable,fileList);        
        context.getJbpmContext().getSession().flush();

        if(filename!=null){
            if(new File(storelocation+"\\"+saveFileName+ticket).delete()){
                context.selectOutcome("success");
                context.addSuccessMessage("File "+filename+" has been successfully deleted.");
            }else{
                context.selectOutcome("error");
                context.setError("File "+filename+" has been removed from the process, but could not be purged from the file system.");
            }
        }else{
            context.setError("File "+filename+" has already been deleted.");
            context.selectOutcome("error");           
        }
    }
}
