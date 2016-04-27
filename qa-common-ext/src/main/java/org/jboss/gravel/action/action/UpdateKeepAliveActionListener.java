package org.jboss.gravel.action.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.jboss.gravel.action.handler.KeepAliveHandler;
import org.jboss.gravel.action.handler.KeepAliveHandler.KeepAliveEntry;

public class UpdateKeepAliveActionListener implements ActionListener, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3118968438534114528L;
	private ValueExpression varExpression;
	
	public UpdateKeepAliveActionListener(ValueExpression varExpression) {
		this.varExpression=varExpression;
	}

	@Override
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		FacesContext ctx = FacesContext.getCurrentInstance();
		try {
            final UIViewRoot viewRoot = ctx.getViewRoot();
            final Map<String,Object> viewRootMap = viewRoot.getAttributes();
            final Map<String,KeepAliveEntry> keepAliveList;
            if (viewRootMap.containsKey("org.jboss.gravel.KeepAliveList")) {
                keepAliveList = (Map<String,KeepAliveEntry>) viewRootMap.get("org.jboss.gravel.KeepAliveList");
            } else {
                keepAliveList = new HashMap<String, KeepAliveHandler.KeepAliveEntry>();
                viewRootMap.put("org.jboss.gravel.KeepAliveList", keepAliveList);
            }
            keepAliveList.put(varExpression.getExpressionString(),new KeepAliveEntry(varExpression, varExpression.getValue(ctx.getELContext())));
        } catch (Exception rex) {
            throw new AbortProcessingException("An exception of type " + rex.getClass().getName() + " occurred: " + rex.getMessage());
        }

	}

}
