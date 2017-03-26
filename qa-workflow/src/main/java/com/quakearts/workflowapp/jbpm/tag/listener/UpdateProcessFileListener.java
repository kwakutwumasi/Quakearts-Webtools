package com.quakearts.workflowapp.jbpm.tag.listener;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.Session;
import org.jboss.logging.Logger;
import org.jbpm.file.def.FileDefinition;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.JbpmJsfContext;

public class UpdateProcessFileListener implements JbpmActionListener {
    private final ValueExpression dataExpression, idExpression, filenameExpression;
    private static Logger log = Logger.getLogger(UpdateProcessFileListener.class);
    
    public UpdateProcessFileListener(ValueExpression dataExpression, ValueExpression idExpression, ValueExpression filenameExpression) {
        this.dataExpression = dataExpression;
        this.idExpression = idExpression;
        this.filenameExpression = filenameExpression;
    }

    public String getName() {
        return "updateProcessFile";
    }

	public void handleAction(JbpmJsfContext context, ActionEvent event) {
        ELContext elcontext;
        FacesContext facescontext;
        InputStream is;
        Long id;
        Object dataValue, idValue;
        try{
            facescontext = FacesContext.getCurrentInstance();
            elcontext = facescontext.getELContext();
            
            dataValue = dataExpression.getValue(elcontext);
            log.trace("dataExpression is '"+dataValue+"'");
            if(dataValue == null){
                return;
            }
            idValue = idExpression.getValue(elcontext);
            log.trace("idExpression is '"+idValue+"'");
            if(idValue == null){
                return;
            }
        }catch(Exception ex){
            context.setError("Cannot upload file",ex);
            return;
        }
        if(dataValue instanceof byte[]){
            log.trace("dataExpression is a valid InputStream");
            is = new ByteArrayInputStream((byte[])dataValue);
        }else{
            context.setError("Cannot update process definition. data is not of type byte[]");
            return;
        }
        if(idValue instanceof Long){
            log.trace("idExpression is a valid Long");
            id = (Long) idValue;
        }else{
            try{
                id = Long.parseLong(idValue.toString());
            }catch (Exception e){
                context.setError("Cannot update process definition. id is not of type Long");
                return;
            }finally{
            	try {
					is.close();
				} catch (Exception e2) {
				}
            }
        }            
        Session sess = context.getJbpmContext().getSession();
        if(sess == null){
            context.setError("Cannot list files. ", new NullPointerException("getGraphSession() returned null."));
            return;            
        }
        Object obj = sess.load(ProcessDefinition.class,id);
        ProcessDefinition pd = (obj instanceof ProcessDefinition)?(ProcessDefinition)obj:null;
        if(pd == null){
            context.setError("Cannot list files. ", new NoSuchElementException("No ProcessDefinition with id "+id+" exists."));
            return;
        }
        String fileName=filenameExpression!=null? filenameExpression.getValue(elcontext).toString():"";
                
        if(fileName.equals("processimage.jpg")||fileName.equals("processdefinition.xml")){
            context.setError("Cannot update file. "+fileName+" is read only.");
            return;            
        }
        FileDefinition fd = pd.getFileDefinition();
        boolean isUpdate = fd.hasFile(fileName);
        fd.addFile(fileName,is);
        sess.flush();
        context.addSuccessMessage(fileName+" has been "+(isUpdate?"updated in ":"added to ")+" process definition "+pd.getName()+" version "+pd.getVersion());
        if(log.isTraceEnabled())
            log.trace(fileName+" has been "+(isUpdate?"updated in ":"added to ")+" process definition "+pd.getName()+" version "+pd.getVersion());
        context.selectOutcome("success");
    }
}
