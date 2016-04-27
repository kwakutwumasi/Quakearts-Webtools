package org.jboss.gravel.action.action;

import java.io.IOException;

import javax.el.ValueExpression;
import javax.faces.event.ActionListener;
import javax.faces.event.ActionEvent;
import javax.faces.event.AbortProcessingException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 */
public final class LogOutActionListener implements ActionListener {
	private ValueExpression urlExpression;
	
    public LogOutActionListener(ValueExpression urlExpression) {
		this.urlExpression = urlExpression;
	}

	public void processAction(ActionEvent event) throws AbortProcessingException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        final ExternalContext externalContext = facesContext.getExternalContext();
        final Object sessionObject = externalContext.getSession(false);
        if (sessionObject != null) {
            // TODO - portlet support
            HttpSession session = (HttpSession) sessionObject;
            session.invalidate();
            
            facesContext.responseComplete();
            try {
				((HttpServletResponse) externalContext.getResponse()).sendRedirect((String)urlExpression.getValue(facesContext.getELContext()));
			} catch (IOException e) {
			}
            
            facesContext.addMessage(null, new FacesMessage("You have been successfully logged out"));
        }
        
    }
}
