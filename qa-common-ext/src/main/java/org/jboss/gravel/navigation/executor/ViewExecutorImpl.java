package org.jboss.gravel.navigation.executor;

import javax.el.ValueExpression;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import java.util.logging.Logger;

/**
 *
 */
public final class ViewExecutorImpl implements NavigationExecutor {
    private static final long serialVersionUID = 1L;

    private final ValueExpression targetExpression;

    public ViewExecutorImpl(final ValueExpression targetExpression) {
        this.targetExpression = targetExpression;
    }

    public void execute(FacesContext context) {
        final ViewHandler viewHandler = context.getApplication().getViewHandler();
        final String viewId = (String) targetExpression.getValue(context.getELContext());
        final UIViewRoot newRoot = viewHandler.createView(context, viewId);
        context.setViewRoot(newRoot);
        log.fine("set new view in FacesContext for " + viewId);
    }

    private static final Logger log = Logger.getLogger("org.jboss.gravel.navigation.executor.ViewExecutorImpl");
}
