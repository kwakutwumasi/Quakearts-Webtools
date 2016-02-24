package com.quakearts.workflowapp.jbpm.tag.listener;

import com.quakearts.workflowapp.jbpm.util.QuakeArtsjBPMFunctions;
import com.quakearts.workflowapp.jbpm.util.process.ProcessFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
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

public class FileUploadListener implements JbpmActionListener {
    
    private final ValueExpression streamExpression, taskExpression,variableExpression,descExpression, filenameExpression;
    private static final Logger log = Logger.getLogger(FileUploadListener.class);
    private static int maxsize = 500;

	public FileUploadListener(ValueExpression streamExpression,
			ValueExpression taskExpression, ValueExpression descExpression,
			ValueExpression variableExpression,
			ValueExpression filenameExpression) {
        this.streamExpression = streamExpression;
        this.taskExpression=taskExpression;
        this.variableExpression=variableExpression;
        this.descExpression=descExpression;
        this.filenameExpression = filenameExpression;
        maxsize = JbpmConfiguration.Configs.getInt("jbpm.server.upload.maxsize");
    }

    public String getName() {
        return "uploadFile";
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void handleAction(JbpmJsfContext context, ActionEvent event) 
    {
        ELContext elcontext;
        FacesContext facescontext;
        InputStream is;
        TaskInstance task;
        String saveFileName="nosaveFileName",variable;
        Object isValue,taskValue,variableValue,descValue="";
        log.trace("PhaseId = "+event.getPhaseId());
        try{
            facescontext = FacesContext.getCurrentInstance();
            elcontext = facescontext.getELContext();
            
            isValue = streamExpression.getValue(elcontext);
            log.trace("streamExpression is '"+isValue+"'");
            if(isValue == null){
                context.setError("Cannot upload file. Stream was null");
                return;
            }
            taskValue = taskExpression.getValue(elcontext);
            log.trace("idExpression is '"+taskValue+"'");
            if(taskValue == null){
                context.setError("Cannot upload file. Task was null");
                return;
            }
            variableValue = variableExpression.getValue(elcontext);
            log.trace("idExpression is '"+variableValue+"'");
            if(variableValue == null){
                context.setError("Cannot upload file. Variable was null");
                return;
            }
        } catch (Exception ex){
            context.setError("Cannot upload file",ex);
            return;
        }
        
        descValue = descExpression.getValue(elcontext);
        if(descValue == null){
            descValue="";
        }
        
        if(isValue instanceof byte[]){
            log.trace("streamExpression is a valid InputStream");
            is = new ByteArrayInputStream((byte[])isValue);
        } else {
            context.setError("Cannot upload file. stream is not of type byte[]");
            return;
        }
        
        if(taskValue instanceof TaskInstance){
            log.trace("streamExpression is a valid InputStream");
            task = (TaskInstance)taskValue;
        }else{
            context.setError("Cannot upload file.");
            try {
                is.close();
			} catch (Exception e) {
			}
            return;
        }
        variable = variableValue.toString().trim();
        
        if(!task.getActorId().equals(context.getJbpmContext().getActorId())){
            context.setError("Cannot upload file. User does not have access to task.");
            try {
                is.close();
			} catch (Exception e) {
			}
            return;                                    
        }
        if(task.getEnd()!=null){
            context.setError("Cannot upload file. Task has ended.");
            try {
                is.close();
			} catch (Exception e) {
			}
            return;                                                    
        }
        
        String tempfilelocation = JbpmConfiguration.Configs.getString("com.quakearts.file.temp.location");
        log.trace("tempfilelocation ='"+tempfilelocation+"'");
        String storelocation = tempfilelocation+"\\jpbm_task_files";
        File loc;
        loc = new File(storelocation);
        if(!loc.exists()){
            log.trace("Making directory "+loc.toURI());
            loc.mkdir();
        }

        SecureRandom rand;
        try {
            rand = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            rand = new SecureRandom();
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

        String fileName= (String) filenameExpression.getValue(elcontext);
        if(fileName==null)
        	fileName = "";
        
        StringBuffer terminator = new StringBuffer();
        terminator.append("_").append(Math.abs(rand.nextLong()));
        int idpoint = fileName.length()>0?fileName.lastIndexOf("."):-1;
        if(idpoint>0 && idpoint<fileName.length()){
            log.trace("extension='"+fileName.substring(idpoint,fileName.length())+"'");
            terminator.append(fileName.substring(idpoint,fileName.length()));
        }
        FileOutputStream fos;
        int countSize = 0;
        try {
            fos = new FileOutputStream(storelocation+"\\"+saveFileName+terminator,false);
            int i = is.read();
            while(i!=-1){
                countSize++;
                fos.write(i);
                i= is.read();
            }
            fos.close();
            if(countSize > maxsize){
                new File(storelocation+"\\"+saveFileName+terminator).delete();
                try {
                    is.close();
    			} catch (Exception e) {
    			}
                throw new IOException("File exceeds maximum upload size of "+Double.valueOf(maxsize/1000)+" KB");
            }
        }catch(IOException e){
            context.setError("Could not save file.",e);
            return;
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Object collObj = task.getVariable(variable);
        Collection fileList;
        if(collObj instanceof Collection){
            fileList = (Collection) collObj;
        }else{
            fileList = new ArrayList();
        }
        fileList.add(new ProcessFile(fileName,terminator.toString(),descValue.toString()));
        task.setVariable(variable,fileList);
        
        context.getJbpmContext().getSession().flush();
        context.addSuccessMessage("File "+fileName+" has been successfully uploaded.");
        context.selectOutcome("success");
    }
}
