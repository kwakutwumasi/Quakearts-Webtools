package org.jboss.gravel.navigation.executor;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

/**
 *
 */
public final class ViewRedirectExecutorImpl extends AbstractRedirectExecutor {
    private static final long serialVersionUID = 1L;

    public ViewRedirectExecutorImpl(final ValueExpression targetExpression, final ValueExpression storeMessagesExpression) {
        super(targetExpression, storeMessagesExpression);
    }

    protected String getTarget(FacesContext context, final String targetAttrValue) {
        return context.getApplication().getViewHandler().getActionURL(context, targetAttrValue);
    }
}
