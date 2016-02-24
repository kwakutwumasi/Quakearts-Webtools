package com.quakearts.workflowapp.jbpm.tag.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.jbpm.db.GraphSession;
import org.jbpm.file.def.FileDefinition;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.jsf.JbpmActionListener;
import org.jbpm.jsf.JbpmJsfContext;

public class SecureListProcessFilesListener implements JbpmActionListener {
    private final ValueExpression idExpression;
    private final ValueExpression targetExpression;
    
    public SecureListProcessFilesListener(ValueExpression idExpression, ValueExpression targetExpression) {
        this.idExpression=idExpression;
        this.targetExpression=targetExpression;
    }

    public String getName() {
        return "listProcessFiles";
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void handleAction(JbpmJsfContext context, ActionEvent event) {
        ELContext elcontext;
        FacesContext facescontext;
        facescontext = FacesContext.getCurrentInstance();
        elcontext = facescontext.getELContext();
        Long id;
        try{
            Object idObj = idExpression.getValue(elcontext);
            if(idObj instanceof Long)
                id = (Long)idObj;
            else
                id = Long.parseLong(idObj.toString());
        }catch(Exception e){
            context.setError("Cannot list files. ",e);
            return;
        }
        GraphSession sess = context.getJbpmContext().getGraphSession();
        if(sess == null){
            context.setError("Cannot list files. ", new NullPointerException("getGraphSession() returned null."));
            return;            
        }
        ProcessDefinition pd = sess.getProcessDefinition(id);
        if(pd == null){
            context.setError("Cannot list files. ", new NoSuchElementException("No ProcessDefinition with id "+id+" exists."));
            return;
        }
        FileDefinition fd = pd.getFileDefinition();
        Collection col;
        try{
            col = fd.getBytesMap().keySet();
        } catch(Exception e){
            context.setError("Cannot list files. ", new NullPointerException("NullPointerException while retrieving file list."));
            return;            
        }
        targetExpression.setValue(elcontext,new ArrayList(col));
        context.selectOutcome("success");
    }
}
