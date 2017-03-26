package com.quakearts.workflowapp.jbpm.tag.listener.beans;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.jbpm.db.GraphSession;
import org.jbpm.file.def.FileDefinition;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.jsf.core.handler.AbstractHandler;
import org.jbpm.jsf.core.impl.JbpmJsfContextImpl;
import com.quakearts.workflowapp.jbpm.tag.xml.searchform.FormField;
import com.quakearts.workflowapp.jbpm.tag.xml.searchform.SearchFormFields;
import static com.quakearts.workflowapp.jbpm.util.QuakeArtsjBPMFunctions.*;

public class SearchFormFieldsBean {

	private static final String SEARCHFILE = "searchform.xml";
	private static final JAXBContext jctx = getcontext();

	private long id;
	private List<FormField> fields;
	
	private static JAXBContext getcontext() {
		try {
			return JAXBContext.newInstance(SearchFormFields.class);
		} catch (JAXBException e) {
			// won't happen;
			throw new RuntimeException();
		}
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public List<FormField> getFields() {
		return fields;
	}

	@SuppressWarnings("unchecked")
	public void changeFormFields(AjaxBehaviorEvent event) {
		FacesContext ctx = FacesContext.getCurrentInstance();
        final Map<String,Object> requestMap = ctx.getExternalContext().getRequestMap();
        final JbpmJsfContextImpl context;
        if (requestMap.containsKey(AbstractHandler.JBPM_JSF_CONTEXT_KEY)) {
            context = (JbpmJsfContextImpl) requestMap.get(AbstractHandler.JBPM_JSF_CONTEXT_KEY);
        } else {
            context = new JbpmJsfContextImpl();
            requestMap.put(AbstractHandler.JBPM_JSF_CONTEXT_KEY, context);
        }
        
        if(context.hasJbpmContext()){
        	if(context.getJbpmContext().getServices()!=null
        			&&context.getJbpmContext().getServices().getTxService()!=null
        			&& context.getJbpmContext().getServices().getTxService().isRollbackOnly())
        		return;//do not run action. previous action rolled back transaction
        }
		
		if(id<=0)
			return;

		fields = (List<FormField>) retrieve("FORM" + id,
				List.class);
		
		if (fields == null) {
			GraphSession sess = context.getJbpmContext().getGraphSession();
			if (sess == null) {
				context.setError("Cannot list files. ",
						new NullPointerException(
								"getGraphSession() returned null."));
				return;
			}

			ProcessDefinition pd = sess.getProcessDefinition(id);
			if (pd == null) {
				context.setError("Cannot list files. ",
						new NoSuchElementException(
								"No ProcessDefinition with id " + id
										+ " exists."));
				return;
			}

			FileDefinition fd = pd.getFileDefinition();

			if (fd.hasFile(SEARCHFILE)) {
				try {
					fields = ((SearchFormFields) jctx.createUnmarshaller()
							.unmarshal(fd.getInputStream(SEARCHFILE))).getFormFields();
					store("FORM" + id, fields);
				} catch (Exception e) {
					ctx.addMessage(	"",
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR,
							"Cannot get searchfields",
							"Please contact system administrator. Exception of type "
									+ e.getClass().getName()
									+ " was thrown. Message is "
									+ e.getMessage()
									+ ". Exception occured whiles fetching form fields"));
				}
			}
		}
	}

}
